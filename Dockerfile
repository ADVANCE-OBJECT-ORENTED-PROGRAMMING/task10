# Use official Java runtime
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy all Java files into container
COPY . /app

# Compile Java files
RUN javac *.java

# Run main class
CMD ["java", "Main"]  # replace Main with your main class name
