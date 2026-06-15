FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/ride-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java","-Dserver.port=8084","-jar","app.jar"]