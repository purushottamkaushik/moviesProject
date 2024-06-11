FROM openjdk:latest
ARG JAR_FILE=target/booking-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} booking-service.jar
ENTRYPOINT ["java","-jar","/booking-service.jar"]