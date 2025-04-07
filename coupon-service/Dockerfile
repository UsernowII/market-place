FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} coupon-service.jar

ENTRYPOINT ["java", "-jar", "/coupon-service.jar"]

EXPOSE 8082