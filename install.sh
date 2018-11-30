#!/usr/bin/env bash
git clone -b master git@github.com:picodotdev/blog-bitix.git
cd blog-bitix
git config --local user.email "pico.dev@gmail.com"
git config --local user.name "pico.dev"
npm install

git clone -b gh-pages git@github.com:picodotdev/blog-bitix.git deploy/
cd deploy
git config --local user.email "pico.dev@gmail.com"
git config --local user.name "pico.dev"

