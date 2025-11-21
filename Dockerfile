# Use official Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy source code (keeping package structure)
COPY src/main/java/ /app/src/main/java/

# Copy libraries
COPY libs/itextpdf-5.5.13.3.jar /app/libs/

# Compile Java files and keep package structure
RUN javac -cp libs/itextpdf-5.5.13.3.jar -d . src/main/java/net/javaguides/*.java

# Set command to run the main class
CMD ["java", "-cp", ".:libs/itextpdf-5.5.13.3.jar", "net.javaguides.Main"]
