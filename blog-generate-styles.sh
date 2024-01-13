#!/usr/bin/env bash
set -e

npm run less-main
hugo gen chromastyles --style=github > themes/bitix/static/assets/css/syntax.css

