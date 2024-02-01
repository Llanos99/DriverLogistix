# DriverLogistix
This microservices-based project provides a system for managing related Company and Driver entities. The application allows making changes to these entities and provides real-time notifications through messages when modifications occur using RabbitMQ messaging queues.


Key Features:

    Related Entities: Data model includes Company and Driver entities, establishing meaningful OneToMany relationship between Company and Driver.
    Data Persistence: Uses a NoSQL database to store and retrieve information efficiently.
    Entity Changes: Allows making changes to Company and Driver entities.
    Real-time Notifications: Implements a notification system to report changes in entities through real-time messages.
    Architecture: Designed with an event driven microservices architecture that allows scalability to meet future growth needs.

Technologies Used:

    Database: MongoDB
    Development Framework: Spring Boot (Backend)
    Real-time Messaging: RabbitMQ
    Programming Language: Java
