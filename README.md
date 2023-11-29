# social-media-app
Spring Social Media Platform API for User Registration , new post creation, modifying the existing post. 

## Overview

Provide an overview of the project, its purpose, and key features.

## Technologies Used

- Spring Boot 3
- Spring Security 6
- JWT
- H2 In-Memory Database
- Flyway for Database Versioning
- Stoplight for API Design
- Junit 5 and Mockito

## Project Structure


## How to Run

### Prerequisites

- Java 17 or higher
- Maven

### Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/Subhasree1506/social-media-app.git
    ```

2. Navigate to the project root:

    ```bash
    cd social-media-app
    ```

### Running the Application

1. Build the project:
   build app-specific-parent before building social-media-app, this generates the source files needed for social-media-app endpoints. Keep both the project in same root directory before running the project.

    ```bash
    mvn clean install
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

4. Access the application:

    Open a web browser and go to http://localhost:8080 and access the endpoints
     H2-database console can be accessed at http://localhost:8080/h2-console

## API Documentation

Explain how to access the API documentation.

- API documentation is created using Stoplight tool.
- documentation can be found at below project directories
-   api-specification-parent/auth-api/api/auth-api.yml
-   api-specification-parent/posts-api/api/posts-api.yml
-   api-specification-parent/user-web-api/api/user-api.yml

