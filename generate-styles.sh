#!/usr/bin/env bash
set -e

npm run less-main
npm run less-main-cookieconsent
hugo gen chromastyles --style=github > themes/bitix/static/assets/css/syntax.css

cp themes/bitix/static/assets/libs/bootstrap-4.4.1-dist/css/bootstrap.min.css themes/bitix/layouts/partials/style-bootstrap.amp.css
cp themes/bitix/static/assets/css/syntax.css themes/bitix/layouts/partials/style-syntax.amp.css
cp themes/bitix/static/assets/css/main.css themes/bitix/layouts/partials/style-main.amp.css

sed -i "s|'../images/|'/blog-bitix/assets/images/|g" themes/bitix/layouts/partials/style-main.amp.css