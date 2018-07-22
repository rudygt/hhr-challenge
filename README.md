# Heaven HR Challenge

## Dependencies

* Maven
* Java 8+ 

## How to run

* cd to the interview folder
* execute mvn package
* execute java -jar target/interview-0.0.1-SNAPSHOT.jar

## Alternate run method using Docker

* docker run -it -p 8080:8080 -v maven-repo:/root/.m2 --rm maven:3.3-jdk-8 bash

** git clone https://github.com/rudygt/hhr-challenge.git

** cd hhr-challenge/interview/

** mvn package

** java -jar target/interview-0.0.1-SNAPSHOT.jar