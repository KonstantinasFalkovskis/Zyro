# Login test for Zyro.com online web builder

## Overview
An automated test framework designed to cover Zyro.com login test.
Project uses [Spring](https://spring.io) with [Lombok](https://projectlombok.org/) & [TestNG](https://testng.org).

## Prerequisites
Dependencies versions can be found in project Maven POM file. Main ones are:
- Java 11+
- Maven (3.6.3)
- Selenium (3.141.59)
- TestNG (6.10)
- Allure (2.10.0)

## Getting started
### Setup
To run tests you will need to:
- Install JAVA Runtime environment: [java 11 download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Download](https://maven.apache.org/download.cgi) and [install](https://maven.apache.org/install.html) Maven
- TestNG, Rest Assured, and Allure reporting are setup by Maven.

Available values for `webDriver.mode` in application.yml:
- `selenium` (default)
- `browserStack`

### Usage
To run test suite:
~~~
$mvn clean test -Dsurefire.suiteXmlFiles=./src/test/resources/suites/zyro/default.xml

