## Overview
- This service handles commands and domain logic related to renting and returning films in our film rental application.

## Technologies Used
- Java
- Spring Boot
- Docker
- Zookeeper
- Kafka
- MongoDB (for event store)
- OpenFeign (for synchronous communication with query service)
- Maven

## Database Mongo
- You have the needed configuration for Mongo event store in application.yml file. Choose your UI tool for mongo (like
MongoDB Compass or any other) and create the specific connection from application.yml.

## Requirements
- You need to have Java JDK 17 installed on your machine and available on your path.
- You need to have Docker compose installed on your machine. If not, feel free to use this link:
https://docs.docker.com/compose/install/
- Once you installed it, check your version with:
> docker-compose -v


## Structure and packages
- com.videostore.rental.cmd.api: Command classes for API requests, controllers and feign client interface.
- com.videostore.rental.cmd.domain: Aggregates, dto response classes and EventsToreRepository.
- com.videostore.rental.cmd.infrastructure: RentalCommandDispatcher and implementation of event sourcing handlers.

## How to Run
### Docker
- I'm providing you the docker-compose.yml.
 Navigate to its location in your terminal and start it in detach mode with command:
> docker-compose up -d

- After this zookeeper and kafka containers should be started successfully. If you don't have their images on your machine,
docker will first pull them from DockerHub and after will create the containers.

- Now, you need to start the mongo and mysql for your event store from command service and mysql for entities in query service.
all four containers should be configured in one network: 'rentalstoreNet'.
> docker run -it -d --name mongo-container -p 27017:27017 --network rentalstoreNet --restart always -v mongodb_data_container:/data/db mongo:latest

> docker run -it -d --name mysql-container -p 3306:3306 --network rentalstoreNet -e MYSQL_ROOT_PASSWORD=Test123! --restart always -v mysql_data_container:/var/lib/mysql mysql:latest

### Start SpringBootApplication:
There are three or more ways to start the app:
- Run mvn spring-boot:run to start the service in terminal
- Navigate to CommandApplication.java and:
- - run it with pressing on green arrow, or 
- - control+Shift+R.

## APIs
- POST /rent: Rent films.
- POST /return: Return films.

## Test your APISs
- Application have basic Swagger documentation configured. You can test the APIs using swagger, Postman( or any other tool).
- http://localhost:5000/swagger-ui/index.html 

## Unit and Integration tests are not covered here
- I can understand how important is test covering for every application, but implementing CQRS design pattern and event sourcing
with Kafka take too much time for the current task, so I decide to not implement them for now, if they are requested I
will implement them additionally.

Thanks for understanding! Enjoy using the rental app!
