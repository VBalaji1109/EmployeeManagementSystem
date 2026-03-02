# 1. Use a stable, lightweight Alpine-based JDK 22 image
FROM eclipse-temurin:22-jdk-alpine

# 2. Create a non-root user for security (Best Practice)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# 3. Set the working directory
WORKDIR /app

# 4. Define an Argument for the JAR location
ARG JAR_FILE=target/*.jar

# 5. Copy the JAR into the container
COPY ${JAR_FILE} app.jar

# 6. Set Environment Variables for performance tuning
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# 7. Expose the Spring Boot port
EXPOSE 8080

# 8. Use a shell-wrapped Entrypoint to allow environment variable expansion
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
