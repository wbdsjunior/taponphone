#!/bin/sh

./mvnw verify && cp ./target/taponphone-api-gateway-*.jar ~/taponphone-api-gateway.jar

java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ~/taponphone-api-gateway.jar
