FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/User-Service-0.0.1-SNAPSHOT.jar /app/app.jar

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]

EXPOSE 0