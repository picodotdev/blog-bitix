$ docker run -it postgres:alpine bash
bash-5.0# PGPASSWORD=A1a-6hRTGNaShFIEGLvp psql -U v-root-app-ydbbHqVq1gYQqsUxMuIc-1565857370 -h 172.17.0.2 -d app
psql (11.4)
Type "help" for help.

app=> \dt
          List of relations
 Schema |  Name   | Type  |  Owner   
--------+---------+-------+----------
 public | product | table | postgres
(1 row)
app=# \du
                                                    List of roles
                 Role name                  |                         Attributes                         | Member of 
--------------------------------------------+------------------------------------------------------------+-----------
 postgres                                   | Superuser, Create role, Create DB, Replication, Bypass RLS | {}
 v-root-app-ydbbHqVq1gYQqsUxMuIc-1565857370 | Password valid until 2019-08-15 09:22:55+00                | {}
app=> quit