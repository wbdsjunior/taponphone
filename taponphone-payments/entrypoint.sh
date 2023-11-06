#!/bin/sh

java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ~/taponphone-payments.jar
