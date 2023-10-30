#!/bin/sh

./mvnw verify && cp ./target/taponphone-payments-*.jar ~/taponphone-payments.jar

java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ~/taponphone-payments.jar
