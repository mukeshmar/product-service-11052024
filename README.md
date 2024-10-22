
# Product Service

## Microservices Overview
This e-commerce application operates using several microservices, each responsible for a specific functionality:
- **[API Gateway](https://github.com/mukeshmar/API-Gateway)**: Routes client requests to the appropriate service.
- **[Payment Service](https://github.com/mukeshmar/payment-service-050624)**: Manages payment transactions.
- **[User Service](https://github.com/mukeshmar/UserService)**: Handles user authentication and profile management.
- **[Email Service](https://github.com/mukeshmar/Email-Service)**: Sends transactional emails.
- **[Service Discovery](https://github.com/mukeshmar/Service-Discovery)**: Manages service registration and discovery.

## Project Overview
This project is a **Product Service API** that manages products for an e-commerce platform. Built using Spring Boot, it operates within a microservices architecture to provide a scalable and fault-tolerant solution for product management. The service supports creating, reading, updating, and deleting products.

## Features
- **Product Management**: Create, read, update, and delete products.
- **Scalability**: Uses microservices architecture for scalability and fault tolerance.

## Technologies Used
- **Spring Boot**: Framework for building web applications.
- **RESTful APIs**: API architecture for communication between services.
- **Microservices Architecture**: For building scalable and fault-tolerant systems.
- **Relational Databases**: Database management system for storing product data.
- **Redis**: In-memory data store for caching and session management.
- **Git**: Version control system for source code management.

## How it Works
1. **Product Request**: The Product Service receives a product request from the client.
2. **Product Processing**: The service processes the request and updates the product database.
3. **Success Response**: Sends a success response to the client if the request is successful.
4. **Error Response**: Sends an error response if the request fails.

### Flow
1. **Client → Product Service (Product Request)**: Client sends a product request.
2. **Product Service → Database (Update Product)**: The service updates the product database.
3. **Product Service → Redis (Cache Update)**: The service updates the Redis cache.
4. **Product Service → Client (Success/Error Response)**: The service sends a success or error response.

## Related Microservices
This Product Service interacts with other microservices in the e-commerce platform. Below are the related microservices:

- **[API Gateway](https://github.com/mukeshmar/API-Gateway)**: Acts as the single entry point for client requests and routes them to the appropriate service.
- **[Payment Service](https://github.com/mukeshmar/payment-service-050624)**: Manages payments and transactions within the platform.
- **[User Service](https://github.com/mukeshmar/UserService)**: Handles user authentication, registration, and profile management.
- **[Email Service](https://github.com/mukeshmar/Email-Service)**: Sends transactional emails such as order confirmations and password resets.
- **[Service Discovery](https://github.com/mukeshmar/Service-Discovery)**: Manages service registration and discovery within the microservices architecture.

