#!/bin/sh

./mvnw verify && cp ./target/taponphone-authentications-*.jar ~/taponphone-authentications.jar

java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ~/taponphone-authentications.jar
