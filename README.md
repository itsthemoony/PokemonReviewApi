# Pokémon Review API 🐾

Welcome to the **Pokémon Review API**, a Spring Boot-based RESTful API designed to manage Pokémon data. This API allows users to view, create, update, and delete Pokémon, as well as retrieve paginated results of Pokémon. Built with Java 21 and PostgreSQL, the API offers a powerful backend solution for any Pokémon-related application. 

---

## 🚀 Features

- **Create Pokémon**: Add a new Pokémon to the database.  
- **Get Pokémon**: Retrieve a list of all Pokémon with pagination.  
- **Get Pokémon by ID**: Fetch details of a Pokémon by its ID.  
- **Update Pokémon**: Modify an existing Pokémon's details.  
- **Delete Pokémon**: Remove a Pokémon from the database.  
- **Pagination**: Fetch Pokémon in pages with customizable page size and number.  

---

## 📋 Requirements

- **Java**: 21  
- **Maven**: 3.6+  
- **Database**: PostgreSQL (local or remote)  
- **IDE**: IntelliJ IDEA / Eclipse / VS Code  

---

## ⚙️ Prerequisites

1. **Install Java 21**  
   Make sure Java 21 is installed and configured on your machine. You can download it from [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) or [OpenJDK](https://openjdk.java.net/).

2. **Install PostgreSQL**  
   You’ll need a running instance of PostgreSQL. If you don’t have it installed, you can follow these guides:  
   - [Installing PostgreSQL on Linux](https://www.postgresql.org/download/linux/)  
   - [Installing PostgreSQL on Windows](https://www.postgresql.org/download/windows/)  
   - [Installing PostgreSQL on macOS](https://www.postgresql.org/download/macosx/)

3. **Install Maven**  
   Install [Maven](https://maven.apache.org/install.html) if you haven’t already. This will help you build and manage the project.

---

## 🛠️ Setup and Installation

### 1. Clone the repository

## 2. Configure the database
In the src/main/resources/application.properties file, update the database connection details to match your PostgreSQL setup:

spring.datasource.url=jdbc:postgresql://localhost:5432/pokemon_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
Replace your_username and your_password with your PostgreSQL credentials.

## 3. Build the project
To build the project, use Maven:

mvn clean install

## 4. Run the application
Once the build is successful, you can run the Spring Boot application:

mvn spring-boot:run
The application will start on port 8080 by default.

## 🧑‍💻 API Endpoints

## 1. Create a Pokémon 🆕
Endpoint: POST /api/pokemons
Request Body:
{
  "name": "Pikachu",
  "type": "Electric"
}
Response: {
  "id": 1,
  "name": "Pikachu",
  "type": "Electric"
}

### 2. Get All Pokémon (Paginated) 📄
Endpoint: GET /api/pokemons?pageNo=0&pageSize=10
Response:
{
  "content": [
    {
      "id": 1,
      "name": "Pikachu",
      "type": "Electric"
    },
    ...
  ],
  "pageNo": 0,
  "pageSize": 10,
  "totalElements": 100,
  "totalPages": 10,
  "last": false
}

### 3. Get Pokémon by ID 🔍
Endpoint: GET /api/pokemons/{id}
Response:
{
  "id": 1,
  "name": "Pikachu",
  "type": "Electric"
}

### 4. Update Pokémon 🔧
Endpoint: PUT /api/pokemons/{id}
Request Body:
{
  "name": "Pikachu",
  "type": "Electric"
}
Response:
{
  "id": 1,
  "name": "Pikachu",
  "type": "Electric"
}

### 5. Delete Pokémon 🗑️
Endpoint: DELETE /api/pokemons/{id}
Response: 200 OK

## 💻 Technologies Used
#### Spring Boot: The backend framework for building the API.
#### Spring Data JPA: For interacting with the database using the repository pattern.
#### PostgreSQL: Relational database to store Pokémon data.
#### Lombok: To reduce boilerplate code for getters, setters, and constructors.
#### Maven: For project build management.

## 📬 Contact
Email: Kheirollahi.mahan@gmail.com

