FROM eclipse-temurin:21
EXPOSE 8080
ADD target/goals-galaxy-api-0.0.1-SNAPSHOT.jar goals-galaxy-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/goals-galaxy-api-0.0.1-SNAPSHOT.jar"]