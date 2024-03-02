#!/usr/bin/env bash

# Parameters
# * $1: ${nextRelease.version}
# * $2: ${lastRelease.version}

echo "$1" > .semantic-release-next-version
echo "$2" > .semantic-release-last-version