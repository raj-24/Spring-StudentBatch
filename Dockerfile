# Use Java 8 lightweight base image
FROM openjdk:8-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy WAR file into container
COPY target/StudentBatch-0.0.1-SNAPSHOT.war StudentBatch-0.0.1-SNAPSHOT.war

# Expose Spring Boot default port
EXPOSE 8080

# Run Spring Boot WAR (embedded Tomcat)
ENTRYPOINT ["java", "-jar", "StudentBatch-0.0.1-SNAPSHOT.war"]

CMD ["--spring.profiles.active=docker"]