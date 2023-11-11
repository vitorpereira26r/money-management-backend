
FROM docker/compose:1.29.2 as compose

COPY docker-compose-mysql.yaml .

RUN docker-compose -f docker-compose-mysql.yaml up -d

FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/money_management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
