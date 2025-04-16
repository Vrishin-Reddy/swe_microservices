FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/*.jar service.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/service.jar"]