#!/usr/bin/env bash
set -e

CA_PATH=../ca
CERTS_PATH=server-certs

export CA_DIR=$CA_PATH
export INTERMEDIATE_DIR=$CA_PATH/intermediate

mkdir -p $CERTS_PATH/certs $CERTS_PATH/csr $CERTS_PATH/private
cp openssl/server/openssl*.conf $CERTS_PATH/
cd $CERTS_PATH

# Server

openssl genrsa \
      -out private/consul-server.key.pem 8192
chmod 400 private/consul-server.key.pem

openssl req -config openssl-consul-server.conf \
      -key private/consul-server.key.pem \
      -subj "/C=ES/ST=Spain/L=Madrid/O=Acme S.A./CN=Consul Server" \
      -new -sha256 -out csr/consul-server.csr.pem