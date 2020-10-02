#!/usr/bin/env bash
docker network create --subnet 172.30.0.0/16 nomad

consul agent -dev -ui -client=0.0.0.0
nomad agent -dev -config=nomad/nomad.conf
# http://127.0.0.1:8500
# http://127.0.0.1:4646