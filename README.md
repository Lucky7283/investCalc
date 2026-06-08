# InvestCalc - Investment Portfolio Manager

InvestCalc is a full-stack Spring Boot application designed to manage an investment fund. It allows users to track a main fund balance, record new investments, and view investment details with real-time balance calculations.

## 🚀 Features

- **Dashboard**: Overview of total and available funds with real-time balance calculation.
- **Investment Management**: Create, view, and list investments with automatic deduction from the available fund.
- **Fund Injection**: Ability to add capital to the main fund balance.
- **Sorting & Filtering**: Browse investments with sorting options by Name, Amount (ASC/DESC), or Date.
- **Delayed Notifications**: Demonstrates asynchronous processing by logging a confirmation 2 minutes after a new investment is recorded.
- **Error Handling**: Custom error page for navigating invalid URLs.

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot 4.0.6**
- **Spring Data JPA**: For efficient data persistence and object-relational mapping.
- **PostgreSQL**: Robust relational database for storing fund and investment records.
- **Thymeleaf**: Server-side template engine for dynamic web pages.
- **Bootstrap 5**: Modern, responsive UI design.
- **Executors**: Scheduled tasks for delayed system notifications.

## 🏗 Project Structure

- `com.investCalc.controllers`: MVC controllers handling web requests and routing.
- `com.investCalc.model.Entity`: JPA entities representing the database schema (`FundEntity`, `InvestmentEntity`).
- `com.investCalc.model.Repositorys`: Data access layer using Spring Data JPA repositories.
- `com.investCalc.model.services`: Business logic layer for fund calculations and investment processing.
- `resources/templates`: HTML views powered by Thymeleaf.
- `resources/static`: Static assets including CSS for custom styling.

## 📋 Database Schema

The application manages two primary entities:

1.  **Fund (`funds` table)**:
    - `id`: Primary Key (Identity)
    - `amount`: Total capital injected into the fund.
2.  **Investment (`investments` table)**:
    - `id`: Primary Key (Identity)
    - `name`: Name of the investment asset.
    - `amount`: Dollar amount invested.
    - `createdAt`: Timestamp of the transaction.

## 🚦 Getting Started

### Prerequisites

- JDK 17 or higher
- PostgreSQL database
- Maven (or use the included wrapper)

### Configuration

Update `src/main/resources/application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Running the Application

1. Clone the repository.
2. Navigate to the project root.
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Access the application at `http://localhost:3000`.

## 📝 Usage

- **Home Page**: Displays the total fund, available balance, and a list of all investments.
- **New Investment**: Click "New Investment" to record a new asset. The system validates if sufficient funds are available.
- **Add Funds**: Inject more capital into the main fund via the "Add to Fund" feature.
- **Details**: View specific transaction details by clicking on an investment name.
