FROM openjdk:latest
ARG JAR_FILE=target/payment-servicee-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} payment-servicee.jar
ENTRYPOINT ["java","-jar","/payment-servicee.jar"]