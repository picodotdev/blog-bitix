#!/usr/bin/env bash
cd deploy/
git add .
git commit -m "Site update at `LC_ALL=en_US.utf8 date -u`"
git push origin gh-pages
cd ..