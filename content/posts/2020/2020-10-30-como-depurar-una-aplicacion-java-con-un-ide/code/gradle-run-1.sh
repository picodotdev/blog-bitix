$ ./gradlew run --debug-jvm
...
Listening for transport dt_socket at address: 5005

$ GRADLE_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005" ./gradlew run
Listening for transport dt_socket at address: 5005