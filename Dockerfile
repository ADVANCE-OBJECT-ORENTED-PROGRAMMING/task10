# Base Java image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy source code
COPY src/main/java/net/javaguides/ /app/net/javaguides/

# Copy external libraries
COPY libs/itextpdf-5.5.13.3.jar /app/libs/

# Compile Java files
RUN javac -cp libs/itextpdf-5.5.13.3.jar -d . net/javaguides/*.java

# Default command to run the application
CMD ["java", "-cp", ".:libs/itextpdf-5.5.13.3.jar", "net.javaguides.Main"]
