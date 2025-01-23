FROM openjdk:21-jdk-slim

# Устанавливаем xvfb
RUN apt-get update && apt-get install -y xvfb

WORKDIR /appBankMaslyak
COPY out/artifacts/Bank_jar/Bank.jar /appBankMaslyak/Mbank.jar

# Запускаем JavaFX через виртуальный дисплей
ENTRYPOINT ["xvfb-run", "--auto-servernum", "java", "-jar", "Mbank.jar"]
