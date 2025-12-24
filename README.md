# Expense Tracker - Backend

## Week 0: Setup & Skeleton
### Goal: Getting ready with MVP without any friction

### Tasks Completed:
1. **GitHub Repos**: Created `expense-tracker-backend` and `expense-tracker-frontend`.

2. **Backend Setup**: 
   - Generated Spring Boot project (start.spring.io).
   - **Dependencies**: Web, JPA, MySQL, Security, Validation, Lombok (removed later).
   - **Structure**: Controller, Service, Repository, Entity/Model, DTO, Config.

3. **Frontend Setup**:
   - Created Vite React App (Tailwind CSS, React Router).
   - **Axios Configuration**: Created a central `axios` instance with `baseURL` and an interceptor placeholder for JWT.

### 🧠 User Concept Notes (Frontend)

#### 📖 Simple Analogy: sending letters
```text
- Imagine you’re sending letters to a company:
- Base URL = the company’s address. Instead of writing it on every letter, you save it in a template. 
- Interceptor = your ID card. Every time you send a letter, the system automatically attaches your ID card so the company knows it’s you.
```

#### ❓ Why use Axios?
Axios is a tool that helps React talk to your backend. We create one central file (e.g., `axios.js`) where we set:
- **Base URL** → so React knows where the backend lives.
- **Interceptor** → a small piece of code that automatically attaches your JWT token to every request so the application is safe.

**Benefit:**
- You don’t repeat the backend URL everywhere.
- You don’t manually add the token each time.
- Your components stay clean and focused on UI.

#### 🔎 What `config` Really Is
In Axios, every request (like `api.get("/expenses")`) is represented by a **configuration object**. This object contains details about the request:
- **The URL** (`/expenses`)
- **The HTTP method** (`GET`, `POST`, etc.)
- **Headers** (like `Authorization`)
- **Body data** (for `POST`/`PUT`)
- **Other options** (timeout, params, etc.)

#### 🧩 Analogy: The Envelope
Think of `config` as the **envelope** for your request:
- It already has the **destination address** (`baseURL + endpoint`).
- It already knows the **type of delivery** (`GET`, `POST`).
- You’re just slipping your **ID card (JWT token)** into the envelope before mailing it.

#### ✅ Key Point
- `config` is **not** a backend file.
- It’s a **JavaScript object created by Axios** that describes the request.
- The **interceptor** lets you *edit* this object before Axios sends it.

#### 👉 Flow Summary
**React → Axios request (`config`) → Interceptor adds JWT → Backend receives request with token → Validates → Responds**

---

4. **Architecture Planning (Backend Modules)**:
   - **Auth Module**: Handles login and registration, implementing JWT and authentication.
   - **User Module**: Basic profile creation for tracking expenses (Optional in MVP).
   - **Category Module**: CRUD operations and support monthly budget report.
   - **Expense Module**: CRUD operations for expenses, filtering, and summary calculations.
   - **Report Module**: Generates reports for overall/month basis and category-wise data for potential dashboard charts.

---

## Week 1: Configuration & Troubleshooting Log
### 🛠️ Issues Resolved & Current Setup

1. **Lombok Removal**:
    - **Issue**: `java.lang.ExceptionInInitializerError` / `TypeTag :: UNKNOWN` in IntelliJ due to JDK 21 compatibility issues.
    - **Fix**: Completely removed Lombok from `pom.xml` and source code. The project now uses standard Java getters/setters/constructors.

2. **Database Configuration**:
    - **Setup**: Configured to use **Local MySQL** instead of Docker container.
    - **Credentials**: `root` / `Kiran@89`
    - **Fix**: Disabled `spring.docker.compose.enabled=false` to prevent port 3306 conflicts with the running local MySQL service.

3. **Security Access**:
    - **Issue**: Application was asking for a generated password on every restart.
    - **Fix**: Hardcoded default credentials for local development in `application.properties`:
        - **Username**: `admin`
        - **Password**: `admin`

4. **Status**:
    - Backend build successful (`mvnw clean install`).
    - Application starts successfully on port 8080.
    - API endpoints are protected (returning HTTP 401 Unauthorized as expected).
   
### Updates:  
#### 📊 Current Progress (As of Dec 24, 2025)
- **Authentication & User Management**
    - JWT-based authentication fully implemented and verified.
    - Login response updated to include `userName` and `userEmail` for frontend personalization.
    - **Forgot Password / Reset Password** feature implemented (Backend API ready).
- **CRUD Operations**
    - Categories: Full CRUD (Create, Read, Update, Delete) with color coding.
    - Expenses: Full CRUD with support for partial updates (PATCH) and monthly filtering.
- **Serialization & DTOs**
    - Optimized API responses using DTOs (`CategoryResponse`, `ExpenseResponse`) to avoid Hibernate proxy issues.
- **Security**
    - Protected all business endpoints; public access only for login and registration.

## 🛠️ Getting Started (Backend)

### Prerequisites
- **Java 21** or higher
- **MySQL** (version 8.0+)
- **Maven** (optional, uses wrapper `mvnw`)

### Setup Instructions
1.  **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd expense-tracker-backend
    ```
2.  **Database Configuration**:
    - Ensure a MySQL database is running locally.
    - Update `src/main/resources/application.properties` with your credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker_db
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD
    ```
3.  **Build the project**:
    ```bash
    ./mvnw clean install
    ```
4.  **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```
    The server will start at `http://localhost:8080`.

---

## 🧪 Sample Working Response (Expense DTO)
```json
{
  "id": 5,
  "amount": 1500,
  "description": "Petrol refill",
  "expenseDate": "2025-12-23",
  "createdAt": "2025-12-23T17:40:00",
  "categoryName": "Fuel",
  "userEmail": "sai@example.com"
}
```

