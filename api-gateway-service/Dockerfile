FROM openjdk:latest
ARG JAR_FILE=target/api-gateway-service-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} gateway.jar
ENTRYPOINT ["java","-jar","/gateway.jar"]