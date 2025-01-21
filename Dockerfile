FROM openjdk:21-jdk-slim
WORKDIR /appBank
COPY
ENTRYPOINT ["java", "-jar", "bank.jar"]