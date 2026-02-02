# Build stage
FROM maven:3.9-eclipse-temurin-23 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build with production profile (includes frontend build)
RUN mvn clean package -Pproduction -DskipTests

# Runtime stage
FROM eclipse-temurin:23-jre
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 7998

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
