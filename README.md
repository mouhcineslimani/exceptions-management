# Exceptions Management

This project provides a comprehensive framework for global exception handling in a Spring Boot application. The primary goal is to centralize exception management, ensuring consistent error responses and robust handling of both Feign client exceptions and standard Spring exceptions.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Custom Exception Handling](#custom-exception-handling)
- [Feign Client Exception Handling](#feign-client-exception-handling)
- [Fallback Mechanism](#fallback-mechanism)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Overview

The project is designed to manage exceptions in a Spring Boot application effectively. It includes global exception handling for various types of exceptions, including those arising from Feign client calls, database operations, validation errors, and more. The project also implements a fallback mechanism for Feign clients to ensure resilience in case of service failures.

## Features

- **Global Exception Handling:** Centralized handling of exceptions using `@RestControllerAdvice`.
- **Custom Exception Handling:** Easily define and handle custom exceptions like `NotFoundException`, `PersonNotFoundException`, etc.
- **Feign Client Exception Handling:** Specialized handling of Feign client exceptions with the ability to define fallback methods.
- **Fallback Mechanism:** Default responses provided in case of Feign client failures.
- **Validation Error Management:** Clear and consistent error messages for validation failures.
- **Custom Error Responses:** Unified structure for all error responses using `ApiResponse`.

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/mouhcineslimani/exceptions-management.git
    ```
2. **Build the Project:**

   ```bash
   cd exceptions-management
   mvn clean install
   ```
3. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```
4. **Access the Application:**

   Open your browser and access the following URL:

   ```
   http://localhost:8080
   ```
5. **Access the Swagger UI:**

   Open your browser and access the following URL:

   ```
   http://localhost:8080/swagger-ui.html
   ```