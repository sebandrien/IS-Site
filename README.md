# IS-Site README

## Project Overview

This project is a Spring Boot application designed to manage and display information about different inventory types. It provides a web interface for clients and administrators to interact with the system. The application uses Spring MVC for handling web requests, Thymeleaf for server-side templating, and a MySQL database for data storage.

## Table of Contents

- [**Project Structure**](#project-structure)
- [**Dependencies**](#dependencies)
- [**Usage**](#usage)
- [**Endpoints**](#endpoints)
- [**Database Configuration**](#database-configuration)
- [**Error Handling**](#error-handling)
- [**Contributing**](#contributing)
- [**License**](#license)

## Project Structure

The project structure follows standard Spring Boot conventions. The main components are:

- **`com.example.is_site.SiteController`**: Controller class handling web requests.
- **`com.example.is_site.SiteEntity`**: Entity class representing data structure.
- **`com.example.is_site.SiteEntityService`**: Service class for managing entities.
- **`resources/templates`**: Thymeleaf templates for rendering HTML views.

## Dependencies

The application has the following dependencies:

- Spring Boot
- Thymeleaf
- MySQL Connector
- Spring JDBC

These dependencies are managed using Maven. Make sure to have Maven installed before building and running the project.

## Usage

To use the application, follow these steps:

1. Clone the repository: `git clone <repository-url>`
2. Configure the database (see [**Database Configuration**](#database-configuration)).
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`
5. Access the application in a web browser: [http://localhost:8080](http://localhost:8080)

## Endpoints

- **`/clientHome`**: Display information for clients.
- **`/clientHome/viewSteel`**: View steel inventory.
- **`/clientHome/viewRail`**: View rail inventory.
- **`/landing`**: Landing page.
- **`/faq`**: Frequently Asked Questions page.
- **`/adminHome`**: Admin home page.
- **`/login`**: Admin login page.

## Database Configuration

The application uses MySQL as the database. Configure the database connection in the `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://<database-host>:<port>/<database-name>
spring.datasource.username=<database-username>
spring.datasource.password=<database-password>
