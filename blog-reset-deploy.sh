#!/usr/bin/env bash
set -e

mv deploy/ deploy-backup/
mkdir deploy/

cd deploy/
git init -b gh-pages
git remote add origin git@github.com:picodotdev/blog-bitix.git
git config --local user.email "pico.dev@gmail.com"
git config --local user.name "pico.dev"
git config --local pull.ff only
git checkout -b gh-pages
cd ..

cp deploy-backup/.gitignore deploy/.gitignore

./site-build.sh

cd deploy/
git add -A
git commit -m "Site reset at `LC_ALL=en_US.utf8 date +%Y-%m-%dT%H:%M:%S%z`"
git push --force origin gh-pages
cd ..

./site-update-build-deploy.sh
