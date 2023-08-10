FROM openjdk:17-jdk-alpine
COPY target/poststamp-service-0.0.1-SNAPSHOT.jar service.jar
ENTRYPOINT ["java","-jar","/service.jar"]