# Application: Kotlin-Play-Magia
This application is developed for fun.
* [Prerequisites](#prerequisites)
* [Getting Started](#getting-started)

## Prerequisites
* Linux or Windows
* Java (JDK8+)
* Kotlin
* Gradle (6.2+)

## Getting Started
- To build the application:
    `gradle clean build`
- To run the unit tests:
    `gradle test`
- To run the unit tests with coverage report:
    `gradle build jacocoTestReport`
    [Link to coverage report](./build/reports/jacoco/test/html/index.html)
- To run application with default input/output file paths (transaction.csv, rates.csv, output.csv):
    `gradle run` 
    or
    `java -jar ./build/libs/fx-test-0.0.1-all.jar`
- To run application with customized file paths:
    `gradle run --args="transactions.csv rates.csv output.csv"` 
    or
    `java -jar ./build/libs/fx-test-0.0.1-all.jar transactions.csv rates.csv output.csv`
