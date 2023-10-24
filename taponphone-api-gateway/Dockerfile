FROM eclipse-temurin:21-alpine
WORKDIR /home/root/taponphone
COPY .mvn/ .mvn
COPY src/ ./src
COPY mvnw pom.xml ./
RUN ./mvnw verify && cp ./target/taponphone-api-gateway-*.jar taponphone-api-gateway.jar
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar taponphone-api-gateway.jar
EXPOSE 8080
