# Employee Management System
Employee Management System
* To add and update employee information by employer
* To view employee information by employee
* Role based access and operations

# Read Me First



# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/)

## Table of Contents

1. [Guides](#Guides)
2. [Prerequisites](#Prerequisites)
3. [Installation](#Installation)
4. [Set Up Environment Variables](#Set-Up-Environment-Variables)
5. [Swagger API](#Swagger-API)
6. [Project Demo](#Project-Demo)

### Guides
The following guides illustrate how to use some features concretely:
Overview
This project is a Spring service designed to book appointment with doctor and view prescription by patients. It includes separate access
to add/update diagnosis and prescription, have patients as per respective doctor.

### Prerequisites
Before you begin, ensure you have met the following requirements:

Java Development Kit (JDK) 17 or later
Apache Maven 3.6.0 or later
An IDE such as IntelliJ IDEA or Eclipse (optional but recommended)
## Git
### Installation
Follow these steps to get the project up and running on your local machine.

* git clone https://github.com/VengateshPrasad5/employee-be.git
* clone the repository in your local
* go to maven and download the dependency
* enable lombok annotations
* cd root directory

###  Set Up Environment Variables
Create a file named application.properties in the root directory of the project and add the necessary environment variables. For example:

spring.application.name=employee-be
spring.datasource.url=jdbc:mysql://endpoint name/db_name
spring.datasource.username= username
spring.datasource.password= password

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

### Swagger API

* To execute the API URL
* http://localhost:8080/swagger-ui/index.html


### Run the Application from My Application
* By Run button or use
* mvn spring-boot:run

### Project Demo
Watch the [project demo video](https://drive.google.com/file/d/1YmjTwstZ2f6ryI5U5imx4s0Dvh1LVfSf/view?usp=sharing) for a quick overview.

!!!Happy Coding!!!




 



