# Spring Boot Form Login Example

This project demonstrates how to add form-based login functionality to a Spring Boot application using Spring Security
and Thymeleaf.

This is the code to the blog post at https://codereviewvideos.com/form-based-login-in-spring-boot-with-spring-security

## Table of Contents

- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Features](#features)
- [Dependencies](#dependencies)
- [Running the Project](#running-the-project)
- [Testing](#testing)
- [License](#license)

## Getting Started

To get started with this project, clone the repository and run the project using your favourite IDE or from the command
line.

```bash
git clone https://github.com/codereviewvideos/spring-security-form-login-example.git
cd spring-security-form-login-example
```

## Project Structure

The main components of the project include:

- **Controllers**: Manage routes for public and private pages.
- **Templates**: Thymeleaf templates for login, public, and private pages.
- **Security Configuration**: Custom Spring Security configuration for login, logout, and access control.

## Features

- Public page accessible to everyone.
- Private page restricted to authenticated users.
- Form-based login with default Spring Security.
- CSRF protection and secure logout.
- Basic automated testing for public/private access and login/logout functionality.

## Dependencies

This project uses the following key dependencies:

- Spring Boot DevTools
- Spring Web
- Thymeleaf
- Spring Security
- Spring Security Test (for testing)

All dependencies can be found in the `pom.xml` file.

## Running the Project

You can run the project from the command line with Maven:

```bash
./mvnw spring-boot:run
```

Once the project is running, navigate to:

- **Public Page**: `http://localhost:8080/public`
- **Private Page**: `http://localhost:8080/private` (requires login)

Use the default username `billy` and the password of `password` to log in.

You can customise these credentials in the `SecurityConfiguration` file.

## Testing

Run automated tests with:

```bash
./mvnw test
```

These tests cover:

- Public page access without login
- Private page access restriction for unauthenticated users
- Successful login and logout functionality
