
FROM openjdk:17
WORKDIR /app
COPY . /app
RUN ./mvnw package -DskipTests
CMD ["java", "-jar", "target/user-management-0.0.1-SNAPSHOT.jar"]
