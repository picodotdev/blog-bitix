#!/usr/bin/env bash
cd deploy/
git pull
cd ..

npm run webpack
npm run less
./hugo --destination="deploy" --source="." --theme="bitix"
# Renombrado assets en feeds
find ./deploy -type f -name "index.xml" -exec sed -i 's/\&\#34;assets\//\&\#34;https:\/\/picodotdev.github.io\/blog-bitix\/assets\//g' {} +
# Soporte para compatibilidad de feeds antiguos
cp deploy/index.xml deploy/atom.xml
mkdir -p deploy/categories/planeta-codigo/
cp deploy/tags/planeta-codigo/index.xml deploy/categories/planeta-codigo/atom.xml
