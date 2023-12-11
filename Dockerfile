# Compile and build the project
FROM gradle:7.4-jdk11 as build-image
RUN mkdir -p /app
COPY . /app
WORKDIR /app
RUN env=${PROFILE} gradle --stacktrace --no-daemon clean build

FROM openjdk:11
COPY --from=build-image /app/build/libs/purchase-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "app.jar"]