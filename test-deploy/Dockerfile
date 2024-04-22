FROM maven:3.8.6-openjdk-17 as Build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.9-jdk-slim
COPY --from=build /target/demo-0.0.1-SHAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
