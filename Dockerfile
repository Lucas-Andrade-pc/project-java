FROM openjdk:22-bullseye  AS DOWNLOAD_STAGE

WORKDIR project
RUN apt-get update && apt-get install -y maven
COPY pom.xml .
RUN mvn dependency:go-offline

FROM download_stage as BUILD
COPY . .
RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:22-bullseye

WORKDIR app
RUN apt-get update && apt-get install -y maven

COPY --from=build /project/target/api-0.0.1-SNAPSHOT.jar /app/api-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]