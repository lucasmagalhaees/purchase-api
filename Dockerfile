# Compile and build the project
FROM gradle:jdk11 as build-image
RUN mkdir -p /app
COPY . /app
WORKDIR /app
RUN env=${PROFILE} databaseUrl=${JDBC_DATABASE_URL} databaseUsername=${JDBC_DATABASE_USERNAME} databasePassword=${JDBC_DATABASE_PASSWORD} gradle --stacktrace --no-daemon clean build

FROM openjdk:11
COPY --from=build-image /app/build/libs/library-api-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "app.jar"]