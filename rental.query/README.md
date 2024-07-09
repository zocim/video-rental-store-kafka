## Overview
- This service manages queries and read operations for our film rental application.

## Technologies Used
- Java
- Spring Boot
- Docker
- Zookeeper
- Kafka
- MySQL (for data storage)
- Feign Client (for synchronous communication with command service)
Maven

## Database MySQL
- You have the needed configuration for Mongo event store in application.yml file. Choose your UI tool for MySQL (like 
MySQL Workbench or any other) and create the specific connection from application.yml.

## Requirements
- You need to have Java JDK 17 installed on your machine and available on your path.
- You need to have Docker compose installed on your machine. If not, feel free to use this link:
 https://docs.docker.com/compose/install/
- Once you installed it, check your version with:
> docker-compose -v

## Structure and packages
- com.videostore.rental.query.api: Read API controllers and service implementation for feign client. 
- com.videostore.rental.query.domain: Entities, mappers and JPA repositories.
- com.videostore.rental.query.infrastructure: Event consumers and event handlers.

## Entities and repositories
- We have three entities here. Film, rental and return. 
- Film can be located in separate service, but I decide to leave it
here to not overcomplicate the things. When you start the QueryApplication, FilmRepository is populated with test data.
- Relationships between Film and Rental entities are not implemented, only OneToOne relationship is set between 
Rental and Return entities, on the Return side only. 

## How to Run
- If you've already started this Docker part when you've configured and started the rental.cmd app then feel free to skip
starting docker containers and move to Run SpringBootApplication. Check if they are already started with:
> docker ps

- If hte four containers are running then go and Run your queryApplication.java, if not follow the DOcker instructions.

### Docker
- I'm providing you the docker-compose.yml. 
Navigate to its location in your terminal and start it in detach mode with command:
>docker-compose up -d 

- After this zookeeper and kafka containers should be started successfully. If you don't have their images on your machine,
docker will first pull them from DockerHub and after will create the containers.  

- Now, you need to start the mongo and mysql for your event store from command service and mysql for entities in query service.
all four containers should be configured in one network: 'rentalstoreNet'.
> docker run -it -d --name mongo-container -p 27017:27017 --network rentalstoreNet --restart always -v mongodb_data_container:/data/db mongo:latest

> docker run -it -d --name mysql-container -p 3306:3306 --network rentalstoreNet -e MYSQL_ROOT_PASSWORD=Test123! --restart always -v mysql_data_container:/var/lib/mysql mysql:latest

### Run SpringBootApplication:
- Run mvn spring-boot:run to start the service in terminal or navigate to QueryApplication.java and run it with pressing
on green arrow, or control+Shift+R.

## APIs that are used for communication with rental.cmd service with goal /rent and /return APIs to be run successfully
- GET /films/filmType: Get filmType for a specific film.
- GET /rentals/{rentalId}: Get RentalDto with a specific rentalId.


## Test your APISs
- Application have basic Swagger documentation configured. You can test the APIs using swagger, Postman( or any other tool).
- http://localhost:5001/swagger-ui/index.html

## Unit and Integration tests are not covered here
- I can understand how important is test covering for every application, but implementing CQRS design pattern and event sourcing
with Kafka take too much time for the current task, so I decide to not implement them for now, if they are requested I
will implement them additionally.

- Thanks for understanding! Enjoy using the rental app!
