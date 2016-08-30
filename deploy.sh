#!/usr/bin/env bash
cd deploy/
git add .
git commit -m "Site update at `LC_ALL=en_US.utf8 date --iso-8601=seconds`"
git push origin gh-pages
cd ..
