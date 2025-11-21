# Use official Java 17 runtime
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy Java source files
COPY src/main/java/net/javaguides/ /app/

# Copy iText JAR
COPY libs/itextpdf-5.5.13.3.jar /app/libs/

# Compile Java files with iText classpath
RUN javac -cp libs/itextpdf-5.5.13.3.jar -d . Main.java PdfExportApp.java


# Run main class with iText in classpath
CMD ["java", "-cp", ".:libs/itextpdf-5.5.13.3.jar", "Main"]
