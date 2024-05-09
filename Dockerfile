FROM openjdk:17-alpine

#RUN mvn package -DskipTests

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} api.jar

ENTRYPOINT ["java", "-jar", "/api.jar"]