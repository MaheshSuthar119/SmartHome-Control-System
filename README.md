🚀 Smart Device Backend (Java + Spring Boot)

A backend application built using Spring Boot, designed to manage smart home/IoT devices.
The project supports adding devices, updating status, retrieving device lists, and storing all records in a MySQL database.

📌 Features

Create smart devices
Get all devices
Update device status
Delete devices
Stores data in MySQL database
Uses Spring Data JPA (Hibernate ORM)
Uses Enum for device type and device status
Automatic timestamp updates (lastUpdated)

🛠️ Tech Stack

Backend: Java, Spring Boot
Database: MySQL
ORM: Hibernate (Spring Data JPA)
Build Tool: Maven
Tools: IntelliJ IDEA / VS Code, Postman, cURL

📂 Project Structure

smart-device-backend
`│── src/main/java/com/example/smartdevice
│   │── controller
│   │   └── DeviceController.java
│   │── service
│   │   └── DeviceService.java
│   │── repository
│   │   └── DeviceRepository.java
│   │── model
│   │       ├── SmartDevice.java
│   │       ├── DeviceStatus.java (ENUM)
│   │       └── DeviceType.java (ENUM)
│   └── SmartDeviceBackendApplication.java
│
│── src/main/resources
│   └── application.properties
│
│── pom.xml
│── README.md`


🗄️ Database Setup (MySQL)
Create database
`CREATE DATABASE smart_devices_db;`

Table is auto-created by Spring Boot
Spring Boot + JPA will automatically create the table:

smart_devices
`(
  id INT PRIMARY KEY AUTO_INCREMENT,
  device_name VARCHAR(255),
  device_type VARCHAR(50),
  status VARCHAR(20),
  battery_level INT,
  last_updated TIMESTAMP
);`

Configure application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/smart_devices_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Running the Project
Using Maven
mvn spring-boot:run

Using Java
java -jar target/smart-device-backend.jar


📡 API Endpoints
✅ 1. Add Device

POST /api/devices
Body
`{
  "deviceName": "Room Light",
  "deviceType": "LIGHT",
  "status": "ON",
  "batteryLevel": 80
}`

✅ 2. Get All Devices
GET /api/devices

✅ 3. Update Status
PUT /api/devices/{id}/status?newStatus=OFF

✅ 4. Delete Device
DELETE /api/devices/{id}

📊 Sample Response
`{
  "id": 1,
  "deviceName": "Room Light",
  "deviceType": "LIGHT",
  "status": "ON",
  "batteryLevel": 80,
  "lastUpdated": "2025-11-22T17:45:30"
}`


🧠 Purpose of the Project

This Java project demonstrates:
Spring Boot REST API creation
MySQL integration using JPA/Hibernate
Practical OOP concepts
Enum usage for device type & status
CRUD operations with real database
How backend systems manage IoT or Smart Devices

🚀 Future Enhancements
Add JWT authentication
Add device categories
Add MQTT or WebSocket support
Add frontend (React/Angular)
Build mobile app for device control
