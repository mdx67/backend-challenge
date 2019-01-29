**Invillia challange result**

### Features:
   - **Store**: It's possible to create, update and find a store.
   - **Order**: *Not implemented yeat*
   - **Payment**: *Not implemented yeat*

### Submodules
Invillia Challange has these submodules:
 - **invillia-application-api**: The interface and common data. Used to integrate with this modulo.
 - **invillia-application**: Perform business rules.
 - **invillia-service**: Core of business rules and validations.
 - **invillia-domain**: Store data and has common domain.
 - **invillia-infrastructure**: Common base classes, utils, exceptions and more.

### Technologies
 - Maven (build)
 - Docker (containerization platform)
 - Kotlin (main lang)
 - JUnit (unit test)
 - Spring Boot (framework)
 - Undertow (embedded application server)
 - Mysql (database)
 
### Functional Docs 
- [APIS RESt docs] After build the project, find restdocs at /invillia-application/target/generate-docs

### Build commands
 - docker-compose up
 - mvn clean install (use -Dspring.profiles.active=test to run test)
 - java -jar invillia-application/target/invillia-application.jar

### Future features
 - Create and management an Order.
 - Integration with "invillia-gateway-connector" to perform payments operations.
 - Payments with job to refound if not confirmed.
 - Asynchronous payment for recurrence.
 - Default error message.
 - Spring secutiry to perform operations.


---------------------------------------------------------------------------------------------------------




# Invillia recruitment challenge

[![Build Status](https://travis-ci.org/shelsonjava/invillia.svg?branch=master)](https://travis-ci.org/shelsonjava/invillia)

![Invillia Logo](https://invillia.com/public/assets/img/logo-invillia.svg)
[Invillia](https://https://www.invillia.com/) - A transformação começa aqui.

The ACME company is migrating their monolithic system to a microservice architecture and you’re responsible to build their MVP (minimum viable product)  .
https://en.wikipedia.org/wiki/Minimum_viable_product

Your challenge is:
Build an application with those features described below, if you think the requirements aren’t detailed enough please leave a comment (portuguese or english) and proceed as best as you can.

You can choose as many features you think it’s necessary for the MVP,  IT’S NOT necessary build all the features, we strongly recommend to focus on quality over quantity, you’ll be evaluated by the quality of your solution.

If you think something is really necessary but you don’t have enough time to implement please at least explain how you would implement it.

## Tasks

Your task is to develop one (or more, feel free) RESTful service(s) to:
* Create a **Store**
* Update a **Store** information
* Retrieve a **Store** by parameters
* Create an **Order** with items
* Create a **Payment** for an **Order**
* Retrieve an **Order** by parameters
* Refund **Order** or any **Order Item**

Fork this repository and submit your code with partial commits.

## Business Rules

* A **Store** is composed by name and address
* An **Order** is composed by address, confirmation date and status
* An **Order Item** is composed by description, unit price and quantity.
* A **Payment** is composed by status, credit card number and payment date
* An **Order** just should be refunded until ten days after confirmation and the payment is concluded.

## Non functional requirements

Your service(s) must be resilient, fault tolerant, responsive. You should prepare it/them to be highly scalable as possible.

The process should be closest possible to "real-time", balancing your choices in order to achieve the expected
scalability.

## Nice to have features (describe or implement):
* Asynchronous processing
* Database
* Docker
* AWS
* Security
* Swagger
* Clean Code
