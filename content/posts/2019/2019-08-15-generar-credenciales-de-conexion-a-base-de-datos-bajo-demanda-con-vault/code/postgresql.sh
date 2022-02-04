$ docker run -it -p "5432:5432" -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres postgres:alpine
$ docker ps 
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
2792b13c36c1        postgres:alpine     "docker-entrypoint.s…"   3 minutes ago       Up 3 minutes        0.0.0.0:5432->5432/tcp   distracted_keldysh
$ docker exec -it 2792b13c36c1 bash
bash-5.0# psql -U postgres
psql (11.4)
Type "help" for help.

postgres=# CREATE DATABASE app;
postgres=# \connect app
You are now connected to database "app" as user "postgres".
app=# CREATE TABLE product (
    id          bigint PRIMARY KEY,
    name       varchar(256) NOT NULL
);
app=# \dt
          List of relations
 Schema |  Name   | Type  |  Owner   
--------+---------+-------+----------
 public | product | table | postgres
(1 row)
$ docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' 2792b13c36c1
172.17.0.2