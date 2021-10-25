#!/usr/bin/env bash
set -e

rm -rf .git

git init -b main
git add .
git commit -m "Initial commit"

git remote add origin git@github.com:picodotdev/blog-bitix.git
git push -u --force origin master
