#!/usr/bin/env bash
set -e

hugo gen chromastyles --style=github > themes/bitix/static/assets/css/syntax.css
