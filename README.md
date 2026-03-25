# Inventory Management System

A simple Spring Boot REST API for managing product inventory with stock tracking and statistics.

## Features

- Product management (create, read, update, delete products)
- Inventory tracking (stock levels, min/max quantities)
- Stock operations (add, remove, update quantities)
- Product search by name and code
- Inventory statistics dashboard
- Low stock alerts
- Stock availability checks
- Health check endpoint

## Technology Stack

- Java 21
- Spring Boot 4.0.4
- Spring Data JPA
- MySQL 8.0
- Maven
- Hibernate

## Project Structure

```
src/
├── main/
│   ├── java/com/inventory/inventory/management/
│   │   ├── controller/
│   │   │   ├── HealthController.java
│   │   │   ├── ProductController.java
│   │   │   └── InventoryController.java
│   │   ├── service/
│   │   │   ├── ProductService.java
│   │   │   └── InventoryService.java
│   │   ├── entity/
│   │   │   ├── Product.java
│   │   │   └── Inventory.java
│   │   ├── dto/
│   │   │   ├── ProductDTO.java
│   │   │   ├── InventoryDTO.java
│   │   │   └── StatisticsDTO.java
│   │   ├── repository/
│   │   │   ├── ProductRepository.java
│   │   │   └── InventoryRepository.java
│   │   └── InventoryManagementApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/inventory/inventory/management/
        └── InventoryManagementApplicationTests.java
```

## Prerequisites

- Java 21 JDK
- MySQL 8.0+
- Maven 3.6+

## Setup and Installation

1. Clone the repository:
```bash
git clone https://github.com/kush-sainii/inventory-management.git
cd inventory-management
```

2. Create MySQL database:
```sql
CREATE DATABASE inventory_db;
```

3. Update database configuration in `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
```

4. Build the project:
```bash
mvn clean compile
```

5. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Product Endpoints

- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products/code/{code}` - Get product by product code
- `GET /api/products/category/{category}` - Get products by category
- `GET /api/products/search?query=<search_term>` - Search products by name or code
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Inventory Endpoints

- `GET /api/inventory` - Get all inventory
- `GET /api/inventory/product/{productId}` - Get inventory by product ID
- `POST /api/inventory/product/{productId}` - Create inventory for product
- `PUT /api/inventory/product/{productId}` - Set stock quantity
- `POST /api/inventory/product/{productId}/add` - Add stock
- `POST /api/inventory/product/{productId}/remove` - Remove stock
- `GET /api/inventory/low-stock` - Get items with low stock
- `GET /api/inventory/available/{productId}/{quantity}` - Check stock availability
- `GET /api/inventory/statistics` - Get inventory statistics

### Health Endpoint

- `GET /api/health` - Check application health

## Example Requests

### Create a Product
```bash
POST /api/products
Content-Type: application/json

{
  "productCode": "PROD001",
  "productName": "Laptop",
  "price": 999.99,
  "description": "High performance laptop",
  "category": "Electronics",
  "supplier": "TechCorp"
}
```

### Create Inventory for Product
```bash
POST /api/inventory/product/1
Content-Type: application/json

{
  "quantity": 50,
  "minQuantity": 10,
  "maxQuantity": 100,
  "warehouseLocation": "Shelf-A1"
}
```

### Search Products
```bash
GET /api/products/search?query=laptop
```

### Get Inventory Statistics
```bash
GET /api/inventory/statistics
```

### Add Stock
```bash
POST /api/inventory/product/1/add
Content-Type: application/json

{
  "quantity": 20
}
```

## Database Schema

The application uses JPA/Hibernate for ORM. Tables are automatically created/updated.

Tables:
- `products` - Product information
- `inventory` - Stock levels and warehouse locations

## Error Handling

The API returns appropriate HTTP status codes:
- 200 OK - Successful GET/PUT request
- 201 CREATED - Successful POST request
- 204 NO CONTENT - Successful DELETE request
- 400 BAD REQUEST - Invalid request data
- 404 NOT FOUND - Resource not found
- 500 INTERNAL SERVER ERROR - Server error

## License

MIT License

## Author

Kush Saini
