# Use a stable Java 17 image from Eclipse Temurin
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy all files to container
COPY . .

# Compile the Java files
RUN javac -d . src/main/java/net/javaguides/*.java

# Run your main Java class (change if needed)
CMD ["java", "net.javaguides.Main"]
