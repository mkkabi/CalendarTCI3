FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/schedule-university-3-3.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]