FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} stats-service.jar

ENTRYPOINT ["java", "-jar", "/stats-service.jar"]

EXPOSE 8081