FROM openjdk:latest
ARG JAR_FILE=target/movies-registry-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} movies-registry.jar
ENTRYPOINT ["java","-jar","/movies-registry.jar"]