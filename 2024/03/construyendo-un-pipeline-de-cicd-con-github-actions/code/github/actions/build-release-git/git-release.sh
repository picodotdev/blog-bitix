#!/usr/bin/env bash
set -eu

# Arguments
## builder: gradle, maven, lerna, npm
## bump: major, minor, patch
#
# Usage
## Get release information
# ./releaser.sh -b gradle -u patch -w patch -t "./gradle-app/taskfile.yml" -i
## Perform in dry-run
# ./releaser.sh -b gradle -u patch -w patch -t "./gradle-app/taskfile.yml" -v -d -p
## Perform
# ./releaser.sh -b gradle -u patch -w patch -t "./gradle-app/taskfile.yml" -v -p

# Global config variables
VERSION_PREFIX="v"
DRY_RUN="false"

function get_snapshot_tag() {
    local BUILDER="$1"
    local SNAPSHOT_TAG=""

    if [ "${BUILDER}" == "gradle" ] || [ "${BUILDER}" == "maven" ] || [ "${BUILDER}" == "npm" ]; then
        SNAPSHOT_TAG="-SNAPSHOT"
    fi

    echo "${SNAPSHOT_TAG}"
}

function get_version() {
    local TASK_FILE="$1"
    local BUILDER="$2"
    local VERSION=""

    BUILDERS=("gradle" "maven" "npm")
    if [[ " ${BUILDERS[*]} " =~ " ${BUILDER} " ]] && [ -z "${VERSION}" ]; then
        VERSION=$(task --taskfile "${TASK_FILE}" build:version)
    fi

    if [ "${BUILDER}" == "lerna" ] && [ -z "${VERSION}" ] ; then
        VERSION=$(echo $(date '+%Y%m%d%H%M%S'))
    fi

    echo "${VERSION}"
}

function set_version() {
    local CONTEXT_DIRECTORY="$1"
    local BUILDER="$2"
    local VERSION="$3"

    if [ "${BUILDER}" == "gradle" ]; then
        find . -name "gradle.properties" | xargs sed -i "s/^[#]*\s*version=.*/version=${VERSION}/"
    fi

    if [ "${BUILDER}" == "maven" ]; then
        ./mvnw versions:set -DnewVersion="${VERSION}"
    fi

    if [ "${BUILDER}" == "npm" ]; then
        npm version "${VERSION}" --no-git-tag-version
    fi
}

function parse_version() {
    local RE='[^0-9]*\([0-9]*\)[.]\([0-9]*\)[.]\([0-9]*\)\([0-9A-Za-z-]*\)'

    local VERSION="$1"
    local COMPONENT="$2"
    local RESULT=""

    local MAJOR=$(echo "${VERSION}" | sed -e "s#${RE}#\1#")
    local MINOR=$(echo "${VERSION}" | sed -e "s#${RE}#\2#")
    local PATCH=$(echo "${VERSION}" | sed -e "s#${RE}#\3#")
    local TAG=$(echo "${VERSION}" | sed -e "s#${RE}#\4#")

    if [ "${COMPONENT}" == "major" ]; then
        RESULT=${MAJOR}
    fi
    if [ "${COMPONENT}" == "minor" ]; then
        RESULT=${MINOR}
    fi
    if [ "${COMPONENT}" == "patch" ]; then
        RESULT=${PATCH}
    fi
    if [ "${COMPONENT}" == "tag" ]; then
        RESULT=${TAG}
    fi

    echo "${RESULT}"
}

function release_version() {
    local VERSION="$1"
    local BUMP="$2"

    if [ "${BUMP}" == "major" ] || [ "${BUMP}" == "minor" ] || [ "${BUMP}" == "patch" ]; then
        local VERSION_MAJOR=$(parse_version ${VERSION} "major")
        local VERSION_MINOR=$(parse_version ${VERSION} "minor")
        local VERSION_PATCH=$(parse_version ${VERSION} "patch")

        local VERSION_BUMP=$(next_version "${VERSION}" "${BUMP}" "")
        local VERSION_BUMP_MAJOR=$(parse_version ${VERSION_BUMP} "major")
        local VERSION_BUMP_MINOR=$(parse_version ${VERSION_BUMP} "minor")
        local VERSION_BUMP_PATCH=$(parse_version ${VERSION_BUMP} "patch")

        local V=""
        # When patch bump then always use the prepared current version without tag
        # 1.0.1-SNAPSHOT and patch then 1.0.1
        if [ "${BUMP}" == "patch" ]; then
          V="${VERSION_MAJOR}.${VERSION_MINOR}.${VERSION_PATCH}"
        fi
        # When minor bump then use the prepared current version if the patch is 0, if not release a next minor
        # 1.1.0-SNAPSHOT and minor then 1.1.0
        # 1.1.3-SNAPSHOT and minor then 1.2.0
        if [ "${BUMP}" == "minor" ]; then
          if [ "${VERSION_PATCH}" == "0" ]; then
            V="${VERSION_MAJOR}.${VERSION_MINOR}.0"
          else
            V="${VERSION_BUMP_MAJOR}.${VERSION_BUMP_MINOR}.0"
          fi
        fi
        # When major bump then use the prepared current version if the minor is 0 and patch is 0, if not release a next major
        # 1.0.0-SNAPSHOT and major then 1.0.0
        # 1.1.3-SNAPSHOT and major then 2.0.0
        # 1.2.0-SNAPSHOT and major then 2.0.0
        if [ "${BUMP}" == "major" ]; then
          if [ "${VERSION_MINOR}" == "0" ] && [ "${VERSION_PATCH}" == "0" ]; then
            V="${VERSION_MAJOR}.0.0"
          else
            V="${VERSION_BUMP_MAJOR}.0.0"
          fi
        fi

        echo "${V}"
    elif [ "${BUMP}" == "date" ]; then
        echo "${VERSION}"
    fi
}

function next_version() {
    local VERSION="$1"
    local BUMP="$2"
    local TAG="$3"

    if [ "${BUMP}" == "major" ] || [ "${BUMP}" == "minor" ] || [ "${BUMP}" == "patch" ]; then
        local VERSION_MAJOR=$(parse_version ${VERSION} "major")
        local VERSION_MINOR=$(parse_version ${VERSION} "minor")
        local VERSION_PATCH=$(parse_version ${VERSION} "patch")
        local VERSION_TAG=$(parse_version ${VERSION} "tag")

        if [ "${BUMP}" == "major" ]; then
            VERSION_MAJOR=$((${VERSION_MAJOR} + 1))
            VERSION_MINOR="0"
            VERSION_PATCH="0"
        fi
        if [ "${BUMP}" == "minor" ]; then
            VERSION_MINOR=$((${VERSION_MINOR} + 1))
            VERSION_PATCH="0"
        fi
        if [ "${BUMP}" == "patch" ]; then
            VERSION_PATCH=$((${VERSION_PATCH} + 1))
        fi

        echo "${VERSION_MAJOR}.${VERSION_MINOR}.${VERSION_PATCH}${TAG}"
    elif [ "${BUMP}" == "date" ]; then
        echo "${VERSION}"
    fi
}

function checks() {
    local CONTEXT_DIRECTORY="$1"
    local BUILDER="$2"
    local VERSION="$3"

    if [ -z "${BUILDER}" ]; then
        echo "No builder provided"
        exit 1
    fi

    if [ "${BUILDER}" == "gradle" ] && [ ! -f "${CONTEXT_DIRECTORY}/gradle.properties" ]; then
        echo "Gradle builder but not file gradle.properties"
        exit 1
    fi

    if [ "${BUILDER}" == "maven" ] && [ ! -f "${CONTEXT_DIRECTORY}/pom.xml" ]; then
        echo "Maven builder but not file pom.xml"
        exit 1
    fi

    if [ -n "$(git status --porcelain)" ]; then
        echo "Not clean git workspace"
        git status
        exit 1
    fi

    if [ -n "$(git ls-remote --tags origin ${VERSION_PREFIX}${VERSION})" ]; then 
        echo "Release git tag exists"
        exit 1
    fi
}

function release_git_add() {
    local BUILDER="$1"

    if [ "${BUILDER}" == "gradle" ]; then
        find . -name "gradle.properties" | xargs git add
    fi

    if [ "${BUILDER}" == "maven" ]; then
        find . -name "pom.xml" | xargs git add
    fi

    if [ "${BUILDER}" == "npm" ]; then
        find . -maxdepth 1 -name "package*.json" | xargs git add
    fi
}

function pre_release() {
    local CONTEXT_DIRECTORY="$1"
    local BUILDER="$2"
    local RELEASE_VERSION="$3"
    local RELEASE_TAG="$4"

    set_version "${CONTEXT_DIRECTORY}" "${BUILDER}" "${RELEASE_VERSION}"
    release_git_add "${BUILDER}"
    git commit -m "[ci skip] [gha-release] Release ${RELEASE_VERSION}"
    git tag -a "${RELEASE_TAG}" -m "Release ${RELEASE_VERSION}"
    git push origin "${RELEASE_TAG}"
    git push origin HEAD
}

function post_release() {
    local CONTEXT_DIRECTORY="$1"
    local BUILDER="$2"
    local VERSION="$3"

    set_version "${CONTEXT_DIRECTORY}" "${BUILDER}" "${VERSION}"
    release_git_add "${BUILDER}"
    git commit -m "[ci skip] [gha-release] Prepare ${VERSION}"
    git push origin HEAD
}

function information() {
  local SNAPSHOT_TAG="$1"
  local BRANCH="$2"
  local IS_RELEASE_BRANCH="$3"
  local CURRENT_VERSION="$4"
  local RELEASE_VERSION="$5"
  local RELEASE_TAG="$6"
  local NEXT_VERSION="$7"
  local VERSION="$8"
  local TAG="$9"

  JSON_STRING=$(jq -n \
    --arg SNAPSHOT_TAG "${SNAPSHOT_TAG}" \
    --arg BRANCH "${BRANCH}" \
    --arg IS_RELEASE_BRANCH "${IS_RELEASE_BRANCH}" \
    --arg CURRENT_VERSION "${CURRENT_VERSION}" \
    --arg RELEASE_VERSION "${RELEASE_VERSION}" \
    --arg RELEASE_TAG "${RELEASE_TAG}" \
    --arg NEXT_VERSION "${NEXT_VERSION}" \
    --arg VERSION "${VERSION}" \
    --arg TAG "${TAG}" \
    '{"snapshot-tag": $SNAPSHOT_TAG, "branch": $BRANCH, "is-release-branch": $IS_RELEASE_BRANCH, "current-version": $CURRENT_VERSION, "release-version": $RELEASE_VERSION, "release-tag": $RELEASE_TAG, "next-version": $NEXT_VERSION, "version": $VERSION, "tag": $TAG}')

  echo "${JSON_STRING}"
}

function perform() {
    local CONTEXT_DIRECTORY="$1"
    local BUILDER="$2"
    local RELEASE_VERSION="$3"
    local RELEASE_TAG="$4"
    local NEXT_VERSION="$5"

    checks "${CONTEXT_DIRECTORY}" "${BUILDER}" "${RELEASE_VERSION}"
    if [ "${DRY_RUN}" == "false" ]; then
        pre_release "${CONTEXT_DIRECTORY}" "${BUILDER}" "${RELEASE_VERSION}" "${RELEASE_TAG}"
        
        if [ -n "${NEXT_VERSION}" ]; then
            post_release "${CONTEXT_DIRECTORY}" "${BUILDER}" "${NEXT_VERSION}"
        fi
    fi
}

function main() {
    local CONTEXT_DIRECTORY="."
    local BUILDER=""
    local BUMP=""
    local BUMP_DEFAULT=""
    local TASK_FILE=""

    local INFORMATION_OPTION="false"
    local PERFORM_OPTION="false"
    local VERBOSE_OPTION="false"

    while getopts "c:b:u:w:t:dip" arg; do
        case $arg in
            c)
                CONTEXT_DIRECTORY="${OPTARG}"
                ;;
            b)
                BUILDER="${OPTARG}"
                ;;
            u)
                BUMP="${OPTARG}"
                ;;
            w)
                BUMP_DEFAULT="${OPTARG}"
                ;;
            t)
                TASK_FILE="${OPTARG}"
                ;;
            d)
                DRY_RUN="true"
                ;;
            i)
                INFORMATION_OPTION="true"
                ;;
            p)
                PERFORM_OPTION="true"
                ;;
            v)
                VERBOSE_OPTION="true"
                ;;
            *) ;;
        esac
    done

    if [ "${VERBOSE_OPTION}" == "true" ]; then
      set -o xtrace
    fi

    local SNAPSHOT_TAG=$(get_snapshot_tag ${BUILDER})
    local BRANCH="$(git branch --show-current)"
    local IS_RELEASE_BRANCH="false"
    local CURRENT_VERSION=$(get_version ${TASK_FILE} ${BUILDER})

    local RELEASE_VERSION=""
    if [[ "${CURRENT_VERSION}" != *"-SNAPSHOT" ]]; then
        RELEASE_VERSION=$(next_version "${CURRENT_VERSION}" "${BUMP}" "")
    else
        RELEASE_VERSION=$(release_version "${CURRENT_VERSION}" "${BUMP}")
    fi

    local RELEASE_TAG="v${RELEASE_VERSION}"

    local NEXT_VERSION=""
    if [[ "${CURRENT_VERSION}" == *"-SNAPSHOT" ]]; then
        NEXT_VERSION=$(next_version "${RELEASE_VERSION}" "${BUMP_DEFAULT}" "${SNAPSHOT_TAG}")
    fi

    local VERSION=""
    local TAG=""

    if [ "${BRANCH}" == "main" ] || [ "${BRANCH}" == "master" ]; then
        VERSION="${RELEASE_VERSION}"
        IS_RELEASE_BRANCH="true"
    else
        VERSION=$(echo "${CURRENT_VERSION}" | sed -e "s|${SNAPSHOT_TAG}|-${BRANCH//\//-}${SNAPSHOT_TAG}|g")
        IS_RELEASE_BRANCH="false"
    fi

    TAG="v${VERSION}"

    if [ "${INFORMATION_OPTION}" == "true" ]; then
      information "${SNAPSHOT_TAG}" "${BRANCH}" "${IS_RELEASE_BRANCH}" "${CURRENT_VERSION}" "${RELEASE_VERSION}" "${RELEASE_TAG}" "${NEXT_VERSION}" "${VERSION}" "${TAG}"
    fi

    if [ "${PERFORM_OPTION}" == "true" ]; then
      perform "${CONTEXT_DIRECTORY}" "${BUILDER}" "${RELEASE_VERSION}" "${RELEASE_TAG}" "${NEXT_VERSION}"
    fi
}

main "$@"
