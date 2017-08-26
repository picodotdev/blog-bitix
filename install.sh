#!/usr/bin/env bash
git clone git@github.com:picodotdev/blog-bitix.git
cd blog-bitix
npm install

mkdir deploy
cd deploy
git clone -b gh-pages git@github.com:picotdev/blog-bitix.git
mv blog-bitix/* .
git config --local user.email "pico.dev@gmail.com"
git config --local user.name "pico.dev"
cd ..
