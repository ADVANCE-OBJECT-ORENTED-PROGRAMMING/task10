# Use a valid OpenJDK 17 image
FROM openjdk:17-jdk

# Copy source code into container
COPY src/main/java /app
WORKDIR /app

# Compile all Java files in the package
RUN javac net/javaguides/*.java

# Expose port if the app uses one (optional)
EXPOSE 8080

# Run the main class
CMD ["java", "net.javaguides.Main"]
