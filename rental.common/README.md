## Overview
- This service contains shared utilities, DTOs, and constants used across our film rental application.

## Technologies Used
- Java
- Spring Boot

## Structure
- com.videostore.rental.common: Main package containing shared components.
- com.videostore.rental.common.dto: DTO classes used for data transfer between services.
- com.videostore.rental.common.enums: Enums for the entities.
- com.videostore.rental.common.errorhandling: Global exception handling logic.
- com.videostore.rental.common.events: Event classes shared between command and query services.

- 
## How to Use
Add common as a dependency in command and query services.
Import DTOs and constants where needed across services.
Use global exception handling in controllers for consistent error responses.

# Additional Notes
Avoid adding business logic in common to maintain separation of concerns.
DTOs should be kept simple and focused on data transfer needs.
