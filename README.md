# Purchase API

This application is designed to store purchase transactions and retrieve them in different currencies using the Treasury Reporting Rates of Exchange API. The goal is to provide a flexible and user-friendly platform for managing and analyzing purchase transactions in various currencies.
## Getting Started

These instructions will give you a copy of the project up and running on
your local machine for development and testing purposes.

### Prerequisites

Requirements for the software and other tools to build, test and push
- Java 11
- Gradle
- Docker

### Run the application locally

Set up the project database using Docker

    docker run --name containerPostgres --restart=always -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -e POSTGRES_DB=purchase_db -p 5432:5432 -d postgres

Run the application with

    ./gradlew bootRun

If you run the application locally, Swagger is available on

http://localhost:9090/purchase-api/swagger-ui.html

### Run the application using Docker

Run the following command line

    docker compose up -d --build

This command line will start up both the Java application and the database.

If you run the application using Docker, Swagger is available on

http://localhost:9092/purchase-api/swagger-ui.html

## Using migrations with Flyway

### Default migration

Executes flyway migration considering dev environment

    ./gradlew flywayMigrate

### Specific environment migration

Executes flyway migration considering specify environment

    ./gradlew flywayMigrate -Penv=qa


## Using Cache

Integration with Treasury API was cacheable to increase the application performance. Cache was configured using Google Guava dependency and a ttl property was set on the project properties.  

## Running Jacoco

Run the following command

    ./gradlew clean build

It will trigger Jacoco test coverage and the report can be found in

    /build/jacoco/test/html/index.html

## Running Sonarqube

Override gradle.properties with your own sonar config and run the following command

    ./gradlew jacocoTestReport sonarqube   

## Running the tests

Tests are divided in modules. There are two modules: test and contractTest

### Run Unit Tests

Tests responsible for assuring of code components' quality

    ./gradlew test

### Run Contract Test

Tests responsible for assuring that API's contract is accurate

    ./gradlew contractTest

### Run All Tests

Run all automate test to assure code quality

    ./gradlew quality

## Stage

Before commiting please run the following command to clean and build the project

    ./gradlew stage


## Authors

- **Lucas Magalhães**
  [GitHub](https://github.com/lucasmagalhaees)

