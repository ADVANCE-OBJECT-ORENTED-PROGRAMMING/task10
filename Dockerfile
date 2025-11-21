# Use official Java runtime
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Java files from GitHub repo
COPY src/main/java/net/javaguides/ /app/

# Compile Java files
RUN javac Main.java PdfExportApp.java

# Run the main class
CMD ["java", "Main"]

