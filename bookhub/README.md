# BookHub

## Table of Contents

1. [Description](#description)
2. [Features](#features)
3. [Tech Stack](#tech-stack)
4. [Setup & Installation](#setup--installation)
5. [API Endpoints](#api-endpoints)
6. [Future Enhancements](#future-enhancements)
7. [License](#license)

## Description

**BookHub** is a full-stack application designed for book lovers to manage their personal collections and connect with a
community of fellow readers. Users can register, securely validate their emails, and manage their book collections with
features for creating, updating, sharing, and archiving books. The platform also supports book borrowing, including
availability checks, return processing, and approval for returned books.

Security is a priority, with Keycloak handling user authentication and authorization. The backend is powered by **Spring
Boot 3** and **Spring Security 6**, while the frontend is built with **Angular** and styled using **Bootstrap**.

## Features

- **User Registration & Authentication**
    - **Secure User Registration:** Users can create new accounts with email-based validation for secure account
      activation.
    - **Email Verification:** Activation of user accounts is completed through a secure email validation process,
      ensuring
      authenticity.
    - **Keycloak-based Authentication:** Provides secure login sessions and access management via Keycloak, handling
      both user authentication and authorization.

- **Book Management**
    - **Personal Book Collections:** Users can add, update, and delete books from their personal collections with ease.
    - **Organized Book Archiving:** Archive books to declutter your collection, keeping it well-organized for future
      reference.
    - **Community Sharing:** Share your book collection with other users, making it discoverable within the community.

- **Book Borrowing System**
    - **Borrow Requests:** Users can request to borrow books from others in the community.
    - **Book Availability Checks:** System ensures books are available for borrowing before confirming requests.
    - **Borrowing Returns:** Users can return borrowed books and manage the return process seamlessly.
    - **Return Approvals:** Book owners can approve or deny return requests to finalize the borrowing cycle.

- **Social Interaction**
    - **Community Connections:** Engage with other users, explore their collections, and build connections based on
      shared interests.
    - **Book Discovery:** Share and discover new books within the community, expanding your literary horizons through
      social interaction.

## Tech Stack

### Backend

- **Java Spring Boot 3**: Provides a robust backend framework to handle REST API requests.
- **Spring Security 6**: Handles authentication and authorization with JWT tokens.
- **Hibernate/JPA**: Manages database interactions with entities and repositories.
- **PostgreSQL**: Database for storing users, books, feedbacks, and transactions.
- **Maven**: Build automation tool that manages dependencies, project structure, and packaging for deployment.
- **Keycloak Authentication**: Utilized for secure authentication and authorization, managing user roles and access to
  protected API endpoints.
- **OpenAPI and Swagger UI Documentation**: Provides interactive API documentation.

## Setup & Installation

### Prerequisites

- Java 17 or higher.
- Maven 3.8.7 or higher.
- PostgreSQL database.
- Keycloak server setup for authentication.

### Backend Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/bookhub.git
   cd bookhub/bookhub

2. Install dependencies:
   ```bash
   ./mvn clean install

3. Set up the database:
   Make sure PostgreSQL is installed and running. Update the `application.properties` file with the correct database
   credentials:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/your_database_name
       username: your_username
       password: your_password
       driver-class-name: org.postgresql.Driver

4. Set up Keycloak:
   Use Docker Compose to deploy a Keycloak server.
   Once the Keycloak server is running, import the pre-configured realm by navigating to the Keycloak admin console and
   using the Import feature. You can find the realm configuration in the /keycloak/realm/bookhub.json file.
   ```bash
   Keycloak Admin Console > Add Realm > Select File (bookhub.json) > Import
   ```
   This will automatically configure the realm, clients, roles, and permissions needed for BookHub.

5. Run the application:
   ```bash
   ./mvn spring-boot:run

## API Endpoints

- By default, the API will run at:
   ```bash
   http://localhost:8088/api/v1

### User Endpoints

- POST `/api/v1/auth/register`: Register a new user.
- POST `/api/v1/auth/authenticate`: Authenticate a user and issue a JWT token.
- GET `/api/v1/auth/activate-account`: Activate a user's account via a verification token.

### Book Endpoints

- GET `/api/v1/books`: Fetch all books.
- POST `/api/v1/books`: Add a new book.
- POST `/api/v1/cover/{bookId}`: Upload a cover image for the specified book.
- POST `/api/v1/borrow/{bookId}`: Borrow a book by its ID.
- PATCH `/api/v1/borrow/return/{bookId}`: Return a borrowed book.
- PATCH `/api/v1/books/return/approve/{bookId}`: Approve the return of a borrowed book.
- PATCH `/api/v1/books/available/{bookId}`: Mark a book as available for borrowing.
- PATCH `/api/v1/books/archived/{bookId}`: Archive a book, making it unavailable for borrowing.
- GET `/api/v1/books/{bookId}`: Fetch details of a specific book by its ID.
- GET `/api/v1/books/returned`: Fetch all books that have been returned.
- GET `/api/v1/books/owner`: Fetch all books owned by the authenticated user.
- GET `/api/v1/books/borrowed`: Fetch all books borrowed by the authenticated user.

### Feedback Endpoints

- POST `/api/v1/feedbacks`: Submit feedback for a book.
- GET `/api/v1/feedbacks/books/{bookId}`: Get feedback for a specific book by its ID.

## Future Enhancements

- Notifications: Push notifications for borrowing requests and return deadlines.
- Enhanced Search Functionality: Improve search capabilities with advanced filters and keyword suggestions to help users
  find books more effectively.
- Book Recommendations: Suggest books based on user preferences and borrowing history.

## License

This project is licensed under the MIT License.
