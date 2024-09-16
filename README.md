Order Management System
A simple Spring Boot application that implements CRUD operations for orders and products using TDD (Test-Driven Development) with JUnit.
Screenshots of test cases passing were provided.

Technologies Used
Java 17
Spring Boot
Spring Data JPA
H2 Database
JUnit TDD (for testing)
Setup and Installation
Clone the repository:

bash
Copy code
git clone https://github.com/tmull1/OrderManagementSystem
cd OrderManagementSystem
Build the project using Maven:

bash
Copy code
mvn clean install
Run the application:

bash
Copy code
mvn spring-boot:run
Running the Tests
To run the unit tests for OrderService and ProductService, execute:

bash
Copy code
mvn test
Endpoints
POST /orders - Create a new order
PUT /orders/{id} - Update an order
DELETE /orders/{id} - Delete an order
POST /products - Create a new product
PUT /products/{id} - Update a product
DELETE /products/{id} - Delete a product
