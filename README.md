# 📚 Book Store API

This is a Spring Boot-based RESTful API for managing a book store. It supports full CRUD operations, ISBN-13 compliant book registration, input validation, and search by title or author. The API is documented using Swagger and can be run locally or inside a Docker container using Docker Compose.

---

## 🚀 Features

- Create, read, update, and delete books
- ISBN-13 standard generation with check digit validation
- Input validation (title and author) with helpful error messages
- Search functionality by title or author
- Pagination and sorting support for listing books
- Swagger UI for API documentation
- MySQL integration with JPA & Hibernate
- RESTful, stateless, and idempotent endpoints
- Easily deployable using Docker and Docker Compose

---

## 🛠️ Technologies Used

- Java 17+ (or higher)
- Spring Boot
- Spring Data JPA
- Maven
- MySQL
- Hibernate
- Swagger / OpenAPI
- Docker & Docker Compose

---

## 📦 Building the Project Locally

### 1. Clone the Repository

Open a terminal and navigate to your desired development directory (e.g., C:\dev\java). Then run:

```bash
git clone https://github.com/siyabongawonderboymdletshe/gic-book-management.git
cd gic-book-management
```

### 2. Generate Executable File

To package the application into a .jar file using Maven wrapper, open a terminal in the root folder:(C:\dev\java\gic-book-management):

```bash
cd C:\dev\java\gic-book-management
./mvnw clean package
```
After the build completes, the executable JAR will be located in the target/ directory:
```bash
target/gic-book-management-0.0.1-SNAPSHOT.jar
```


### 3. Start the Application

### 🚀 Option 1: Use Free MySQL Database (freesqldatabase.com)
By default, the application is pre-configured to connect to a free remote MySQL server:

```bash
 spring.datasource.url: jdbc:mysql://sql3.freesqldatabase.com:3306/sql3785536?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
```

To start the application using this database, run:

```bash
java -jar target/gic-book-management-0.0.1-SNAPSHOT.jar
```

### 🐳 Option 2: Run with Docker Compose (Local MySQL + App)
If you prefer a fully containerized setup with both the MySQL database and the app running in Docker:

1. Make sure Docker is installed and running on your machine
2. See the `How to Install Docker` section below if needed.

Then simply run:

```bash
docker-compose up --build
```

This will:

- Build the app JAR inside a container

- Spin up a local MySQL instance

- Launch the Spring Boot app with the correct database settings



### 4. Access the API and Docs
Once the application is running (via JAR or Docker), you can access the API and other resources using the links below:

#### 🧪 API Documentation (Swagger)
Explore the endpoints visually using Swagger UI:

```bash
http://localhost:8080/swagger-ui/index.html
```
---

#### 📬 Postman Collection
A pre-built Postman collection is available for testing all API endpoints. Import it into Postman via:

File Location:
```bash
test/postman/collection/GIC Book Management.postman_collection.json
```
Steps to Use:

1. Open Postman

2. Click Import → Choose the file above

3. Use the requests under the "GIC Book Management" collection

This includes:

| Method | Endpoint                    | Description               |
| ------ | --------------------------- | ------------------------- |
| POST   | `/api/books`                | ✅ **Add a book**            |
| GET    | `/api/books`                | 🧾 **Get all books**            |
| GET    | `/api/books/{id}`           | 🔍 **Get book by ID**           |
| PUT    | `/api/books/{id}`           | ✏️ **Update book**               |
| DELETE | `/api/books/{id}`           | ❌ **Delete book**              |
| GET    | `/api/books/search/{query}` | 🔎 **Search book**   |

---
#### 🛢️ Database Access

| Environment          | Host                       | Port   | Access                                            |
| -------------------- | -------------------------- | ------ | ------------------------------------------------- |
| **Local via Docker** | `localhost`                | `3306` | Access with tools like DBeaver                    |
| **Remote (FreeSQL)** | `sql3.freesqldatabase.com` | `3306` | View via [phpMyAdmin](https://www.phpmyadmin.co/) |


##### 📌 Remote MySQL Credentials:
```bash
Host:     sql3.freesqldatabase.com
DB Name:  sql3785536
User:     sql3785536
Password: dQHtbnv1zk
Port:     3306
```
You can manage and inspect data online using: https://www.phpmyadmin.co/

---
## 🐳 How to Install Docker

### 🔹 Step 1: Download Docker Desktop

Go to the [official Docker website](https://www.docker.com/products/docker-desktop/) and download Docker Desktop for your OS:

- **Windows**: Requires WSL 2 backend (installer will guide you)
- **macOS**: Compatible with Intel and Apple Silicon
- **Linux**: Follow instructions for your specific distro

### 🔹 Step 2: Install Docker Desktop

1. Run the downloaded installer.
2. Follow the on-screen setup instructions.
3. After installation, restart your machine if prompted.

### 🔹 Step 3: Verify Installation

Open a terminal and run:

```bash
docker --version
docker-compose --version
```

If both commands return versions, you're ready to go!

---