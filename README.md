# Heaven HR Challenge

## Dependencies

* Maven
* Java 8+ 

## How to run

* cd to the interview folder
* execute mvn package
* execute java -jar target/interview-0.0.1-SNAPSHOT.jar

## Alternate run method using Docker

1. docker run -it -p 8080:8080 -v maven-repo:/root/.m2 --rm maven:3.3-jdk-8 bash
	* the following commands go inside the shell opened by the previous command (inside the container)
	* git clone https://github.com/rudygt/hhr-challenge.git
	* cd hhr-challenge/interview/
	* mvn package
	* java -jar target/interview-0.0.1-SNAPSHOT.jar
	
## Usage 
[Screenshots](screenshots/README.md) 

1. H2 Console ( JDBC URL : "jdbc:h2:mem:hhrdb" ) 
	* http://localhost:8080/h2db/
2. Swagger UI ( this allow to test all api methods using the browser )
	* http://localhost:8080/swagger-ui.html
3. Api Endpoints (gets)
	* http://localhost:8080/api/v1/joboffers
	* http://localhost:8080/api/v1/joboffers/1
	* http://localhost:8080/api/v1/jobapplications
	* http://localhost:8080/api/v1/jobapplications/1
	
## Tests
- [Unit Tests](interview/src/test/java/com/heavenhr/interview/JobOfferControllerTest.java)
- [Integration Tests](interview/src/test/java/com/heavenhr/interview/JobOfferControllerIntegrationTest.java)
- [Web Integration Tests](interview/src/test/java/com/heavenhr/interview/JobOfferControllerWebIntegrationTest.java)

## Relevant Items
- [Custom Hibernate Interceptor](interview/src/main/java/com/heavenhr/interview/model/JobApplicationListener.java) Used to capture changes to the job application status entity and broadcast them to the ApplicationStatusService 
- [Job Application Status Service](interview/src/main/java/com/heavenhr/interview/service/ApplicationStatusServiceImpl.java) Used to handle the status changes and execute the appropiate action depending on the new application status value. 
- [Job Offer Service](interview/src/main/java/com/heavenhr/interview/service/JobOfferServiceImpl.java) Used manage JobOffer entities. 
- [Job Application Service](interview/src/main/java/com/heavenhr/interview/service/JobApplicationServiceImpl.java) used to manage JobApplication entities. 
- [Custom Exceptions](interview/src/main/java/com/heavenhr/interview/exception/) Exceptions mapped to the proper http status codes. 
- [Data Transfer Objects](interview/src/main/java/com/heavenhr/interview/dto) dtos. 
- Additional the Job Application entity has and audit log over the status changes using Hibernate Envers http://hibernate.org/orm/envers/
