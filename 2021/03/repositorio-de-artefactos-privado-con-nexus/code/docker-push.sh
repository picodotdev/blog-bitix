$ docker images
REPOSITORY        TAG       IMAGE ID       CREATED        SIZE
busybox           latest    a9d583973f65   2 days ago     1.23MB
sonatype/nexus3   latest    f07af611e0df   8 days ago     662MB
$ docker tag a9d583973f65 localhost:8082/repository/docker/busybox:latest
$ docker images
REPOSITORY                                 TAG       IMAGE ID       CREATED        SIZE
busybox                                    latest    a9d583973f65   2 days ago     1.23MB
localhost:8082/repository/docker/busybox   latest    a9d583973f65   2 days ago     1.23MB
sonatype/nexus3                            latest    f07af611e0df   8 days ago     662MB
$ docker login localhost:8082
Username: admin
Password:
Login Succeeded
$ docker push localhost:8082/repository/docker/busybox:latest
The push refers to repository [localhost:8082/repository/docker/busybox]
2983725f2649: Pushed 
latest: digest: sha256:410a07f17151ffffb513f942a01748dfdb921de915ea6427d61d60b0357c1dcd size: 527