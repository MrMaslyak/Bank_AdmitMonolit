FROM openjdk:21-jdk-slim
WORKDIR /appBankMaslyak
COPY out/artifacts/Bank_jar/Bank.jar /appBankMaslyak/Mbank.jar
ENTRYPOINT ["java", "-jar", "bank.jar"]