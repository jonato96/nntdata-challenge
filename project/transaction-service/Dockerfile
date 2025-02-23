FROM openjdk:17-jdk-alpine
COPY target/transaction-service*.jar ms-transaction.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "ms-transaction.jar"]