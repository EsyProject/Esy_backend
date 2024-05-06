FROM openjdk:17-jdk-alpine
ARG JAR_FILE=out/artifacts/apiInSpringBoot_jar/*.jar
COPY ${JAR_FILE} api.jar
ENTRYPOINT ["java", "-jar", "/api.jar"]