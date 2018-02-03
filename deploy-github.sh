#!/usr/bin/env bash
cd deploy/
find . -type f -name '.DS_Store' -delete
git add .
git commit -m "Site update at `LC_ALL=en_US.utf8 date +%Y-%m-%dT%H:%M:%S%z`"
git push origin gh-pages
cd ..
