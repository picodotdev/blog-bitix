
#!/usr/bin/env bash

# Checks Maven and Gradle dependencies vulnerabilities using OWASP plugins.
# https://owasp.org/www-project-dependency-check/

WORKDIR="repositories"
USER=picodotdev
REPOSITORIES=()
REGEXP="log4j-core"

function main() {
    mkdir -p $WORKDIR
    cd $WORKDIR

    for I in ${!REPOSITORIES[@]}; do
        REPOSITORY=${REPOSITORIES[${I}]}

        echo "Checking $REPOSITORY (https://github.com/$USER/$REPOSITORY)"

        if [ ! -d "$REPOSITORY" ]; then
            git clone "git@github.com:$USER/$REPOSITORY.git"
        else
            (cd $REPOSITORY && git pull origin)
        fi

        if [ -f "$REPOSITORY/pom.xml" ]; then
            checkMaven $REPOSITORY
        elif [ -f "$REPOSITORY/build.gradle" -o -f "$REPOSITORY/settings.gradle" ]; then
            checkGradle $REPOSITORY
        else
            echo "Not checkeable project $REPOSITORY (not detected as Maven or Gradle project)"
        fi
    done
}

function checkMaven() {
    REPOSITORY=$1
    (cd $REPOSITORY && echo "Checking $REPOSITORY dependencies..." && mvn --settings ../../settings.xml org.owasp:dependency-check-maven:6.5.2.1:check); EXIT_CODE=$?
    #(cd $REPOSITORY && echo "Checking $REPOSITORY dependencies..." && mvn --settings ../../settings.xml org.owasp:dependency-check-maven:6.5.2.1:check) | grep -E "$REGEXP" | sort | uniq; EXIT_CODE=${PIPESTATUS[0]}
    if [ "$EXIT_CODE" != "0" ]; then
        echo "Check not completed with success ($EXIT_CODE)"
    fi
}

function checkGradle() {
    REPOSITORY=$1
    (cd $REPOSITORY && echo "Checking $REPOSITORY dependencies..." && gradle --init-script ../../init.gradle dependencyCheckAnalyze); EXIT_CODE=$?
    #(cd $REPOSITORY && echo "Checking $REPOSITORY dependencies..." && gradle --init-script ../../init.gradle dependencyCheckAnalyze) | grep -E "$REGEXP" | sort | uniq; EXIT_CODE=${PIPESTATUS[0]}
    if [ "$EXIT_CODE" != "0" ]; then
        echo "Check not completed with success ($EXIT_CODE)"
    fi
}

main
