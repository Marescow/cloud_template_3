/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
/**
 * This service is based on the NPM package called rollup-plugin-angular
 * The reason we rewrite it locally is because it has stopped maintainess and dependency libs have security issues
 * You can find original package from below git:
 * https://github.com/cebor/rollup-plugin-angular
 */

import * as colors from 'colors';
import MagicString from 'magic-string';
import { createFilter } from 'rollup-pluginutils';

const path = require('path');
const fs = require('fs');
const replace = require('replace');

const moduleIdRegex = /moduleId\s*:(.*)/g;
const componentRegex = /@Component\(\s?{([\s\S]*)}\s?\)$|type:\s?Component,\s?args:\s?\[\s?{([\s\S]*)},\s?\]/gm;
const commentRegex = /\/\*[\s\S]*?\*\/|([^\\:]|^)\/\/.*$/gm; // http://www.regextester.com/?fam=96247
const templateUrlRegex = /templateUrl\s*:(.*)/g;
const styleUrlsRegex = /styleUrls\s*:(\s*\[[\s\S]*?\])/g; // http://www.regextester.com/?fam=98594
const stringRegex = /(['"`])((?:[^\\]\\\1|.)*?)\1/g;

const insertText = async (
    str: string,
    dir: any,
    preprocessor = (res: any, text?: any) => res,
    processFilename = false,
    sourceType = 'ts'
): Promise<string>  => {
    let preprocessorPromise: Promise<string>[] = [];

    let quoteChar = sourceType === 'ts' ? '`' : '"';
    str.replace(stringRegex,  (match, quote, url) => {
        const includePath = path.join(dir, url);
        if (processFilename) {
            preprocessorPromise.push(preprocessor(includePath));
        } else {
            const text = fs.readFileSync(includePath).toString();
            preprocessorPromise.push(preprocessor(text, includePath));
        }
        return '';
    });
    const results = await Promise.all(preprocessorPromise);
    return str.replace(stringRegex, (match, quote, url) => {
        return quoteChar + results.shift() + quoteChar;
    });
};

export const angularPlugin = (options: any) => {
    options.preprocessors = options.preprocessors || {}; // set default preprocessors to `{}`
    options.replace = typeof options.replace === 'boolean' ? options.replace : true; // set default replace to `true`

    // ignore @angular/** modules
    options.exclude = options.exclude || [];
    if (typeof options.exclude === 'string' || options.exclude instanceof String) {
        options.exclude = [options.exclude];
    }
    if (options.exclude.indexOf('node_modules/@angular/**') === -1) {
        options.exclude.push('node_modules/@angular/**');
    }

    const filter = createFilter(options.include, options.exclude);

    return {
        name: 'angular',
        async transform(source: string, map: string) {
            if (!filter(map)) return;
            // replace comments in source
            source = source.replace(commentRegex, '');
            // use MagicString
            const magicString = new MagicString(source);
            // get dir from `map`
            const dir = path.parse(map).dir;
            // get file extension from `map`
            const fileExt = map.split('.').pop();

            let hasReplacements = false;
            let match;
            let start, end, replacement;

            const replaceTemplateUrl = async (subString: string, url: string) => {
                hasReplacements = true;
                const insertTextStr = await insertText(
                    url,
                    dir,
                    options.preprocessors.template,
                    options.processFilename,
                    options.sourcetype
                );
                const toReplace = 'template:' + insertTextStr;
                if (fileExt === 'js' && options.replace === true) {
                    /* replace templateUrl in files generated by ngc */
                    replace({
                        regex: subString,
                        replacement: toReplace,
                        paths: [map],
                        recursive: true,
                        silent: true
                    });
                    console.info(
                        `templateUrl in file ${map} has been changed from ${colors.green(
                            subString
                        )}  to ${colors.green(toReplace)}`
                    );
                }
                return toReplace;
            };

            const replaceStyleUrls = async (subString: string, urls: string) => {
                hasReplacements = true;
                const insertTextStr = await insertText(
                    urls,
                    dir,
                    options.preprocessors.style,
                    options.processFilename,
                    options.sourcetype
                );
                const toReplace = 'styles:' + insertTextStr;
                /* replace styles in files generated by ngc */
                if (fileExt === 'js' && options.replace === true) {
                    replace({
                        regex: styleUrlsRegex,
                        replacement: toReplace,
                        paths: [map],
                        recursive: true,
                        silent: true
                    });
                    console.info(
                        `styleUrls in file ${map} has been changed from ${colors.green(
                            subString
                        )} to ${colors.green(toReplace)}`
                    );
                }
                return toReplace;
            };

            while ((match = componentRegex.exec(source)) !== null) {
                start = match.index;
                end = start + match[0].length;
                const replaceTemplateUrlPromise: Promise<string>[] = [];

                match[0].replace(templateUrlRegex, (subString: string, url: string) => {
                    replaceTemplateUrlPromise.push(replaceTemplateUrl(subString, url));
                    return '';
                });
                const replaceTemplateUrlResult = await Promise.all(replaceTemplateUrlPromise);
                const temp = match[0].replace(templateUrlRegex, (subString: string, url: string) => {
                    return replaceTemplateUrlResult.shift() + '';
                });

                const replaceStyleUrlsPromise: Promise<string>[] = [];
                temp.replace(styleUrlsRegex, (subString: string, urls: string) => {
                    replaceStyleUrlsPromise.push(replaceStyleUrls(subString, urls));
                    return '';
                });
                const replaceStyleUrlsResult = await Promise.all(replaceStyleUrlsPromise);
                replacement = temp
                    .replace(styleUrlsRegex, (subString: string, urls: string) => {
                        return replaceStyleUrlsResult.shift() + '';
                    })
                    .replace(moduleIdRegex, function (match, moduleId) {
                        hasReplacements = true;
                        return '';
                    });
                if (hasReplacements) {
                    magicString.overwrite(start, end, replacement);
                }
            }

            if (!hasReplacements) {
                return Promise.resolve();
            }

            let sourceMap: any;
            let result = { code: magicString.toString(), map: sourceMap };

            if (options.sourceMap !== false) {
                result.map = magicString.generateMap({ hires: true });
            }
            return result;
        }
    };
};