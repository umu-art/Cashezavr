FROM bellsoft/liberica-openjdk-alpine:latest
LABEL authors="vikazeni"
COPY /target/cherry-core-1.0-ALPHA.jar /opt/app.jar
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]
