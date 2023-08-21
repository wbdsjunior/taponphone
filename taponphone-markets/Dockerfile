FROM openjdk:17
WORKDIR /taponphone
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
RUN ./mvnw verify
RUN cp ./target/taponphone-markets-*-SNAPSHOT.jar taponphone-markets.jar
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar taponphone-markets.jar
