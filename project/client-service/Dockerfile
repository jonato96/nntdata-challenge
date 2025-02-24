FROM openjdk:17-jdk-alpine
COPY target/client-service*.jar ms-client.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ms-client.jar"]