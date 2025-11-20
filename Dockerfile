# Base image Java
FROM openjdk:17

# Copy source code into container
COPY src/main/java /app
WORKDIR /app

# Compile Java files
RUN javac net/javaguides/*.java

# Expose port if needed
EXPOSE 8080

# Run main class
CMD ["java", "net.javaguides.Main"]
