#!/bin/sh

./mvnw verify && cp ./target/taponphone-markets-*.jar ~/taponphone-markets.jar

java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ~/taponphone-markets.jar
