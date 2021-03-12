$ docker ps
CONTAINER ID   IMAGE             COMMAND                  CREATED          STATUS          PORTS                              NAMES
6b7d7495ad0c   sonatype/nexus3   "sh -c ${SONATYPE_DI…}"   50 seconds ago   Up 49 seconds   0.0.0.0:8081-8082->8081-8082/tcp   nexus
$ docker exec -it 6b7d7495ad0c bash
# cat /nexus-data/admin.password
41693644-b54a-4aef-9cf0-cb151e9ef440