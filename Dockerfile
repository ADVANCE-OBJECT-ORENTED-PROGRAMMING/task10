# Base image Java
FROM openjdk:17

# Copy source code into container
COPY src/main/java /app
WORKDIR /app

# Compile Java files
RUN javac net/javaguides/*.java

# Expose port if your app is web-based
EXPOSE 8080

# Run the main class
CMD ["java", "net.javaguides.Main"]
