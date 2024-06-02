FROM openjdk:23-oracle
LABEL authors="vikazeni"
COPY /target/cherry-production-1.0-ALPHA.jar /opt/app.jar
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]
