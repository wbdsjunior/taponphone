FROM eclipse-temurin:17
WORKDIR /taponphone
COPY .mvn/ .mvn
COPY src/ ./src
COPY mvnw pom.xml ./
RUN ./mvnw verify && cp ./target/taponphone-markets-*.jar taponphone-markets.jar
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar taponphone-markets.jar
EXPOSE 8080
