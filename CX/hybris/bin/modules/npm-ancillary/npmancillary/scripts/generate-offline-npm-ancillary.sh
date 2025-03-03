#!/bin/sh
# Generate Offline NPM Ancillary Module
# Supports: Linux or Mac
# 1. Downloads the smartedit-module and npm-ancillary-module artifact from either the release repository or the snapshot repository.
# 2. Call 'prepare-offline-cache' to generate offline 3rd party libs
# 3. Zips the npm-ancillary module along with the .cache folder and uploads to artifactory(release or snapshot).
OS_NAME="$(uname -s)"

SAP_RELEASE_REPO_ID="milestones"
SAP_SNAPSHOT_REPO_ID="snapshots"
ARTIFACTORY_REPOSITORY_ID="hybris-repository"

SAP_RELEASE_REPO="https://common.repositories.sap.ondemand.com/artifactory/deploy-milestones-cx-commerce-maven"
SAP_SNAPSHOT_REPO="https://common.repositories.sap.ondemand.com/artifactory/deploy-snapshots-cx-commerce-maven"
ARTIFACTORY_RELEASE_REPO="https://repository.hybris.com/hybris-release"
ARTIFACTORY_SNAPSHOT_REPO="https://repository.hybris.com/hybris-snapshot"

PROJECT_GROUPID="de.hybris.platform"
NPMANCILLARY_PROJECT_ARTIFACTID="npm-ancillary-module"
SMARTEDIT_PROJECT_ARTIFACTID="smartedit-module"
PERSONALIZATION_ARTIFACTID="personalization-module"
PERSONALIZATION_SEARCH_ARTIFACTID="personalizationsearch-module"
MERCHANDISING_ARTIFACTID="merchandising-module"

ARTIFACT_VERSION=$1
WORKSPACE_DIRECTORY=$2

if [[ "${ARTIFACT_VERSION}" == "" ]] ; then
    echo "Local usage: ./upload-offline-npm-ancillary.sh ARTIFACT_VERSION"
    echo "Example local usage: ./upload-offline-npm-ancillary.sh 6.6.0.0-RC4-SNAPSHOT"
    exit -1
fi

if [[ "${WORKSPACE_DIRECTORY}" == "" ]] ; then
    WORKSPACE="$(pwd)/build"
else
    WORKSPACE="${WORKSPACE_DIRECTORY}"
fi

NPM_MODULE_HOME="${WORKSPACE}/hybris/bin/ext-content"
SMARTEDIT_EXTENSION_HOME="${WORKSPACE}/hybris/bin/ext-content/smartedit"
CMSSMARTEDIT_EXTENSION_HOME="${WORKSPACE}/hybris/bin/ext-smartedit/cmssmartedit"
P_SMARTEDIT_EXTENSION_HOME="${WORKSPACE}/hybris/bin/ext-content/personalizationsmartedit"
P_SEARCH_SMARTEDIT_EXTENSION_HOME="${WORKSPACE}/hybris/bin/ext-content/personalizationsearchsmartedit"
M_SMARTEDIT_EXTENSION_HOME="${WORKSPACE}/hybris/bin/ext-integration/merchandising/merchandisingsmartedit"
SMARTEDIT_TOOLS_HOME="${WORKSPACE}/hybris/bin/ext-content/smartedittools"
NPM_RESOURCE_HOME="${NPM_MODULE_HOME}/npmancillary/resources/npm"
OFFLINE_DIRECTORY=offline
OUTPUT_SCRIPT='s|\(.*\)outputToParse\(.*\)|\2|p'


if [[ "${ARTIFACT_VERSION}" == *SNAPSHOT ]] ; then
    SAP_REPOSITORY_ID="$SAP_SNAPSHOT_REPO_ID"
    SAP_TARGET_REPO="$SAP_SNAPSHOT_REPO"
    ARTIFACTORY_TARGET_REPO="$ARTIFACTORY_SNAPSHOT_REPO"
else
    SAP_REPOSITORY_ID="$SAP_RELEASE_REPO_ID"
    SAP_TARGET_REPO="$SAP_RELEASE_REPO"
    ARTIFACTORY_TARGET_REPO="$ARTIFACTORY_RELEASE_REPO"
fi

if [ "${OS_NAME}" = "Darwin" ] ; then
    NODE_HOME="${NPM_RESOURCE_HOME}/node/node-v20.18.2-darwin-x64/bin"
    OFFLINE_PROJECT_ARTIFACT_ID="offline-darwin-${NPMANCILLARY_PROJECT_ARTIFACTID}"
elif [ "${OS_NAME}" = "Linux" ] ; then
    NODE_HOME="${NPM_RESOURCE_HOME}/node/node-v20.18.2-linux-x64/bin"
    OFFLINE_PROJECT_ARTIFACT_ID="offline-linux-${NPMANCILLARY_PROJECT_ARTIFACTID}"
fi

echo """
Running upload-offline-npm-ancillary.sh

OS_NAME: "${OS_NAME}"
WORKSPACE: "${WORKSPACE}"

Repository Information:
SAP_REPOSITORY_ID: "${SAP_REPOSITORY_ID}"
ARTIFACTORY_REPOSITORY_ID: "${ARTIFACTORY_REPOSITORY_ID}"
SAP_TARGET_REPO: "${SAP_TARGET_REPO}"
ARTIFACTORY_TARGET_REPO: "${ARTIFACTORY_TARGET_REPO}"

Project Information:
PROJECT_GROUPID: "${PROJECT_GROUPID}"
NPMANCILLARY_PROJECT_ARTIFACTID: "${NPMANCILLARY_PROJECT_ARTIFACTID}"
SMARTEDIT_PROJECT_ARTIFACTID: "${SMARTEDIT_PROJECT_ARTIFACTID}"
ARTIFACT_VERSION: "${ARTIFACT_VERSION}"

NPM_RESOURCE_HOME: "${NPM_RESOURCE_HOME}"

NODE_HOME: "${NODE_HOME}"
OFFLINE_PROJECT_ARTIFACT_ID: "${OFFLINE_PROJECT_ARTIFACT_ID}"

"""

# Create workspace and download artifacts
# rm -rf $WORKSPACE
# mkdir -p $WORKSPACE
cd $WORKSPACE

#echo "Downloading npm-ancillary-module artifact with dest=${WORKSPACE}/${NPMANCILLARY_PROJECT_ARTIFACTID}-${ARTIFACT_VERSION}.zip"
#mvn org.apache.maven.plugins:maven-dependency-plugin:2.4:get \
#    -Dartifact=${PROJECT_GROUPID}:${NPMANCILLARY_PROJECT_ARTIFACTID}:${ARTIFACT_VERSION}:zip \
#    -Ddest=${WORKSPACE}/${NPMANCILLARY_PROJECT_ARTIFACTID}-${ARTIFACT_VERSION}.zip
#echo "Unzipping npm-ancillary artifact"
#unzip -oqq ${WORKSPACE}/${NPMANCILLARY_PROJECT_ARTIFACTID}-${ARTIFACT_VERSION}.zip
#
#echo "Downloading smartedit-module artifact with dest=${WORKSPACE}/${SMARTEDIT_PROJECT_ARTIFACTID}-${ARTIFACT_VERSION}.zip"
#mvn org.apache.maven.plugins:maven-dependency-plugin:2.4:get \
#    -Dartifact=${PROJECT_GROUPID}:${SMARTEDIT_PROJECT_ARTIFACTID}:${ARTIFACT_VERSION}:zip \
#    -Ddest=${WORKSPACE}/${SMARTEDIT_PROJECT_ARTIFACTID}-${ARTIFACT_VERSION}.zip
#echo "Unzipping smartedit artifact"
#unzip -oqq ${WORKSPACE}/${SMARTEDIT_PROJECT_ARTIFACTID}-${ARTIFACT_VERSION}.zip
#
## get latest version of personalization
#P_LATEST=$(mvn -U hybris-versions:get-latest-version \
#-DgroupId=${PROJECT_GROUPID} \
#-DartifactId=${PERSONALIZATION_ARTIFACTID} \
#-DproductVersion=10000.0 -DoutputPrefix=outputToParse | sed -n ${OUTPUT_SCRIPT})
#
#echo "Downloading ${PERSONALIZATION_ARTIFACTID}-${P_LATEST}"
#mvn org.apache.maven.plugins:maven-dependency-plugin:2.4:get \
#-Dartifact=${PROJECT_GROUPID}:${PERSONALIZATION_ARTIFACTID}:${P_LATEST}:zip \
#-Ddest=${WORKSPACE}/${PERSONALIZATION_ARTIFACTID}-${P_LATEST}.zip
#echo "Unzipping personalization artifact"
#unzip -oqq ${WORKSPACE}/${PERSONALIZATION_ARTIFACTID}-${P_LATEST}.zip
#
## get latest version of personalizationsearch
#P_SEARCH_LATEST=$(mvn -U hybris-versions:get-latest-version \
#-DgroupId=${PROJECT_GROUPID} \
#-DartifactId=${PERSONALIZATION_SEARCH_ARTIFACTID} \
#-DproductVersion=10000.0 -DoutputPrefix=outputToParse | sed -n ${OUTPUT_SCRIPT})
#
#echo "Downloading ${PERSONALIZATION_SEARCH_ARTIFACTID}-${P_SEARCH_LATEST}"
#mvn org.apache.maven.plugins:maven-dependency-plugin:2.4:get \
#-Dartifact=${PROJECT_GROUPID}:${PERSONALIZATION_SEARCH_ARTIFACTID}:${P_SEARCH_LATEST}:zip \
#-Ddest=${WORKSPACE}/${PERSONALIZATION_SEARCH_ARTIFACTID}-${P_SEARCH_LATEST}.zip
#echo "Unzipping personalizationsearch artifact"
#unzip -oqq ${WORKSPACE}/${PERSONALIZATION_SEARCH_ARTIFACTID}-${P_SEARCH_LATEST}.zip
#
## get latest version of merchandising and it has different groupId with above
#M_LATEST=$(mvn -U hybris-versions:get-latest-version \
#-DgroupId=com.hybris.merchandising \
#-DartifactId=${MERCHANDISING_ARTIFACTID} \
#-DproductVersion=10000.0 -DoutputPrefix=outputToParse | sed -n ${OUTPUT_SCRIPT})
#
#echo "Downloading ${MERCHANDISING_ARTIFACTID}-${M_LATEST}"
#mvn org.apache.maven.plugins:maven-dependency-plugin:2.4:get \
#-Dartifact=com.hybris.merchandising:${MERCHANDISING_ARTIFACTID}:${M_LATEST}:zip \
#-Ddest=${WORKSPACE}/${MERCHANDISING_ARTIFACTID}-${M_LATEST}.zip
#echo "Unzipping merchandising artifact"
#unzip -oqq ${WORKSPACE}/${MERCHANDISING_ARTIFACTID}-${M_LATEST}.zip

echo "Updating PATH for node binary to $NODE_HOME"
export PATH="$NODE_HOME:$PATH"

echo "NPM_MODULE_HOME $NPM_MODULE_HOME"

echo "We need to copy verdaccio-lib from local repo since we already exclude from artifact"
cp -r "$NPM_MODULE_HOME/npmancillary/resources/npm/verdaccio/verdaccio-lib" "${NPM_RESOURCE_HOME}/verdaccio/verdaccio-lib"

echo "Set & Repair npm permission"
if [ "${OS_NAME}" = "Darwin" ] ; then
    sh "${NPM_RESOURCE_HOME}/repairnpm.sh" darwin
elif [ "${OS_NAME}" = "Linux" ] ; then
    sh "${NPM_RESOURCE_HOME}/repairnpm.sh" linux
else
    echo "Do nothing"
fi

sh prepare-offline-cache.sh "" "$NPM_MODULE_HOME" "$SMARTEDIT_TOOLS_HOME" "$SMARTEDIT_EXTENSION_HOME" "$CMSSMARTEDIT_EXTENSION_HOME" "$P_SMARTEDIT_EXTENSION_HOME" "$P_SEARCH_SMARTEDIT_EXTENSION_HOME" "$M_SMARTEDIT_EXTENSION_HOME"

echo "Zipping offline ancillary"
cd "$WORKSPACE/offline/"
zip -ryq "${WORKSPACE}/${OFFLINE_PROJECT_ARTIFACT_ID}-${ARTIFACT_VERSION}.zip" ./hybris
