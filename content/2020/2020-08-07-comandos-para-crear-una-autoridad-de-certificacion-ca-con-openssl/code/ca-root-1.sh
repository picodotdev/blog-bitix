#!/usr/bin/env bash
set -e

CA_PATH=ca

export CA_DIR=.
export INTERMEDIATE_DIR=./intermediate

mkdir -p $CA_PATH/certs $CA_PATH/crl $CA_PATH/newcerts $CA_PATH/private $CA_PATH/intermediate/
cp openssl/ca/openssl*.conf $CA_PATH/
cp openssl/intermediate/openssl*.conf $CA_PATH/intermediate/
cd $CA_PATH

chmod 700 private
touch index.txt
echo 1000 > serial

# CA

openssl genrsa -out private/ca.key.pem 8192
chmod 400 private/ca.key.pem