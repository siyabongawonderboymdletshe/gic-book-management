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

#### 🔁 Option 1: Automated Setup via .bat Scripts
You can use a batch script to download the project, build the JAR, and optionally run it with or without Docker — all with one click.

⬇️ Download Setup Scripts:

- 🖥️ Without Docker: [setup-and-run.bat](https://github.com/siyabongawonderboymdletshe/gic-book-management/blob/main/setup-and-run.bat)
- 🐳 With Docker: [setup-and-run-docker.bat](https://github.com/siyabongawonderboymdletshe/gic-book-management/blob/main/setup-and-run-docker.bat)

 Place the downloaded .bat file in your working directory (e.g., C:\dev\java) and double-click it or run it from terminal.

 ⚠️ These scripts assume Java and Docker (if applicable) are installed and configured on your system.

  The project will be extracted into:
  - %USERPROFILE%\dev\gic-book-management-{local or docker}

#### 📥 Option 2: Download ZIP
You can download the source code directly without installing Git:

- ⬇️ [Download ZIP](https://github.com/siyabongawonderboymdletshe/gic-book-management/archive/refs/heads/main.zip)

- Extract the contents to your desired folder (e.g., C:\dev\java\gic-book-management)

#### 🔧 Option 3: Clone Using Git
🛠️ Prerequisite: Make sure Git is installed on your machine. You can verify this by running in a terminal:
```bash
git --version
```

Open a terminal and navigate to your desired development directory (e.g., C:\dev\java). Then run:

```bash
git clone https://github.com/siyabongawonderboymdletshe/gic-book-management.git
cd gic-book-management
```

### 2. Generate Executable File

📝 Note: You only need Java installed locally if you're building or running the JAR manually

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
A pre-built Postman collection is available for testing all API endpoints. Import it into Postman.

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
### 5. Database Access

### 🌐 Connect to the Remote MySQL Database (FreeSQLDatabase.com)
This project is also configured to use a free public MySQL database.

You can manage and inspect data online using: https://www.phpmyadmin.co:

##### 📌 Credentials:
```bash
Host:     sql3.freesqldatabase.com
DB Name:  sql3785536
User:     sql3785536
Password: dQHtbnv1zk
Port:     3306
```

##### 🔗 Connect via GUI (DBeaver, MySQL Workbench)
- Open your preferred database tool
- Create a new MySQL connection
- Enter the credentials above
- Connect and browse the data


### 🐋 Connect to the Local Docker MySQL Database
Once the containers are running via:
```bash
docker-compose up --build
```
You can connect to the MySQL database running inside the container.

#### 🔧 Option 1: Access from Inside the Container (MySQL CLI)
Run the following command from your terminal to open a MySQL shell:
```bash
docker exec -it gic-book-mysql mysql -u root -p
```
Enter the root user password: `rootpassword`

Once inside, select the database and view records:
```bash
USE bookstoredb;
SELECT * FROM book;
```

#### 🖥️ Option 2: Access via MySQL GUI (e.g., DBeaver, MySQL Workbench)
Use the following credentials to connect using a GUI tool:
```bash
Host:     localhost
Port:     3306
User:     root
Password: rootpassword
Database: bookstoredb
```
If connection is denied, check:
- Docker is running
- Port 3306 is mapped
- User root exists with host = '%'

🛠️ If needed, connect via CLI and run:

```bash
CREATE USER 'root'@'%' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;
```
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