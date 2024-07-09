## Overview
- The video-rental-store-kafka project serves as parent project for: 
- - rental.cmd, 
- - rental.query, and 
- - rental.common services.
- Except these three child projects, additionally you need to set up the cqrs.core as maven module.

## Run the rental app
- You only need to run two service: rental.cmd and rental.query projects, and only they have databases. 
- - rental.cmd is using Mongo for storing events
- - rental.query is using MySQL for storing JPA entities.
- cqrs.core is a framework project for all projects. 
- rental.common is not runnable too, it contains the dtos for rental.cmd and rental.query. 


