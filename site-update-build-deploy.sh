#!/usr/bin/env bash
set -e

./site-update.sh
./site-build.sh
./site-deploy.sh