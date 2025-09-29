$ docker exec -it broker1 kafka-topics --bootstrap-server broker1:9092 --create --partitions 2 --replication-factor 2 --topic quickstart
