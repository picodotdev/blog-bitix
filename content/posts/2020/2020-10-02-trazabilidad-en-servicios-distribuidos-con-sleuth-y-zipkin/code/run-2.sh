#!/usr/bin/env bash
docker network create --subnet 172.30.0.0/16 nomad

./gradlew assemble

nomad job run nomad/traefik.nomad
nomad job run nomad/zipkin.nomad
# http://127.0.0.1:8092
# http://127.0.0.1:9411

nomad job run nomad/configserver.nomad
nomad job run nomad/service.nomad
nomad job run nomad/client.nomad