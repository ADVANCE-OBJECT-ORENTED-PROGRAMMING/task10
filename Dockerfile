# Use Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy source code
COPY src/main/java/net/javaguides/ /app/net/javaguides/

# Copy libraries
COPY libs/itextpdf-5.5.13.3.jar /app/libs/

# Compile Java files into the package structure
RUN javac -cp libs/itextpdf-5.5.13.3.jar net/javaguides/*.java

# Run the Main class
CMD ["java", "-cp", ".:libs/itextpdf-5.5.13.3.jar", "net.javaguides.Main"]
