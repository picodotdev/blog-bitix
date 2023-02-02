$ docker exec -it broker1 kafka-console-consumer --bootstrap-server broker1:9092 --topic quickstart --from-beginning
$ docker exec -it broker1 kafka-console-consumer --bootstrap-server broker1:9092 --topic quickstart
$ docker exec -it broker1 kafka-console-consumer --bootstrap-server broker1:9092 --group consumer-group-1 --topic quickstart
