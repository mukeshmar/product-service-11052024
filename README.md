# Product Service

## Project Overview

This project is a Product Service API that manages products for an e-commerce platform. It is built using Spring Boot and uses a microservices architecture. The service provides a scalable and fault-tolerant solution for product management, allowing clients to create, read, update, and delete products.

## Features

* **Product Management**: Create, read, update, and delete products
* **Scalability**: Built using microservices architecture for scalability and fault tolerance

## Technologies Used

* **Spring Boot**: Framework for building web applications
* **RESTful APIs**: API architecture for communication between services
* **Microservices Architecture**: Architecture for building scalable and fault-tolerant systems
* **Relational Databases**: Database management system for storing product data
* **Redis**: In-memory data store for caching and session management
* **Git**: Version control system for source code management

## How it Works

1. **Product Request**: The Product Service receives a product request from the client.
2. **Product Processing**: The Product Service processes the product request and updates the product database.
3. **Success Response**: If the product request is successful, the Product Service sends a success response to the client.
4. **Error Response**: If the product request fails, the Product Service sends an error response to the client.

## Flow

1. **Client -> Product Service (Product Request)**: The client sends a product request to the Product Service.
2. **Product Service -> Database (Update Product)**: The Product Service updates the product database.
3. **Product Service -> Redis (Cache Update)**: The Product Service updates the Redis cache.
4. **Product Service -> Client (Success/Error Response)**: The Product Service sends a success or error response to the client.
