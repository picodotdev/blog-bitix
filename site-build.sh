#!/usr/bin/env bash
set -e

./blog-generate-styles.sh

#https://github.com/gohugoio/hugo/issues/6699
#hugo --minify
hugo --destination="deploy"

# Soporte para compatibilidad de feeds antiguos
cp deploy/index.xml deploy/atom.xml
mkdir -p deploy/categories/planeta-codigo/
cp deploy/tags/planeta-codigo/index.xml deploy/categories/planeta-codigo/atom.xml
