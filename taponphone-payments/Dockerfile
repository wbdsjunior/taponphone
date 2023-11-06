FROM eclipse-temurin:21-alpine

RUN addgroup -g 1000 java \
 && adduser -u 1000 -G java -s /bin/sh -D java

USER java

WORKDIR /home/java/work

COPY .mvn/ .mvn

COPY src/ ./src

COPY entrypoint.sh mvnw pom.xml ./

RUN ./mvnw verify \
 && cp ./target/taponphone-payments-*.jar ~/taponphone-payments.jar

ARG JAVA_OPTS

ENV JAVA_OPTS=$JAVA_OPTS

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080
