# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Create app folder inside container
WORKDIR /app

# Copy all project files into container
COPY . .

# Compile the Java files
RUN javac -d . src/main/java/net/javaguides/*.java

# Run the main class (change name if needed)
CMD ["java", "net.javaguides.Main"]
