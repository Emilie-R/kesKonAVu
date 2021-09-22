FROM openjdk:8-jdk-alpine
LABEL version="0.0.1"
ARG JAR_FILE=target/kesKonAVu-exposition-1.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} keskonavu-app.jar
ENTRYPOINT ["java","-jar","keskonavu-app.jar"]