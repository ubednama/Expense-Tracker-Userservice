FROM openjdk:21
LABEL authors="ubednama"

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the host to the container
COPY userService-0.0.1-SNAPSHOT.jar /app/userService-0.0.1-SNAPSHOT.jar

# Expose the port that your Java service listens on
EXPOSE 9810

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "/app/userService-0.0.1-SNAPSHOT.jar"]