/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import { SecurityContext } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

import { SeDowngradeService } from '../../../../../di';

@SeDowngradeService()
export class GenericEditorSanitizationService {
    constructor(private domSanitizer: DomSanitizer) {}

    isSanitized(content: string): boolean {
        const tempDiv = document.createElement('div');
        const tempComparedDiv = document.createElement('div');
        const sanitizedContent = this.domSanitizer
            .sanitize(SecurityContext.HTML, content)
            .replace(/&#10;/g, '\n')
            .replace(/&#160;/g, '\u00a0')
            .replace(/<br>/g, '<br />');
        tempDiv.innerHTML = sanitizedContent;
        const originalContent = content
            .replace(/&#10;/g, '\n')
            .replace(/&#160;/g, '\u00a0')
            .replace(/<br>/g, '<br />');
        tempComparedDiv.innerHTML = originalContent;
        return tempDiv.innerHTML === tempComparedDiv.innerHTML;
    }
}
