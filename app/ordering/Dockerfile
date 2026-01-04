# Build stage
FROM maven:3.9-eclipse-temurin-23 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build Vaadin frontend and package
RUN mvn vaadin:prepare-frontend vaadin:build-frontend && \
    mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:23-jre
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port (Vaadin default is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
