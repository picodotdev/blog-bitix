$ docker run --name postgres -e POSTGRES_PASSWORD=password postgres
$ docker exec -it postgres /bin/bash
root@9e6463f25eaf:/# psql --username=postgres
psql (13.1 (Debian 13.1-1.pgdg100+1))
Type "help" for help.

postgres=#