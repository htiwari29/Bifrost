# Bifrost

Bifrost is an authentication service designed to provide a centralized and secure authentication mechanism for your applications. Inspired by the concept of a single point of authentication in the THOR movies, Bifrost ensures that your users' identities are protected and verified across various services.

## Features

- Bifrost provides a RESTful API for user authentication and token management
- Secure JWT (JSON Web Token) creation and validation.
- Integration with Spring Security for enhanced authentication and authorization.
- Implementation of HATEOAS principles to provide navigable APIs.
- Centralized user session management for seamless user experience.

## Getting Started

These instructions will help you set up and run the Bifrost Authentication Service on your local machine for development and testing purposes.

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Gradle
- MongoDB

### Installation

1. **Clone the repository:**

   ```shell
   git clone https://github.com/yourusername/bifrost-authentication.git
   cd bifrost-authentication

2. **Build the project**
  
   ```shell
   gradle build

3. **Run the springboot app**



## Usage

Here are some of the key endpoints:

- `/v1/user/register`: Register a user in the database.
- `/v1/user/login`: Authenticate a user and obtain a JWT.
- `/v1/user/logout`: Invalidate a user's session and log them out.


## Configuration

The Bifrost Authentication Service can be configured through the `application.properties` file. You can customize settings such as token expiration, security configurations, and more.

## Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and write tests if needed.
4. Ensure that the existing tests pass: `gradle test`.
5. Commit your changes and push to your forked repository.
6. Submit a pull request with a clear description of your changes.


## Acknowledgments

- The creators of Spring Boot, Spring Security, and JWT for providing the foundational tools for this project.
- The THOR movies for inspiring the name and concept of Bifrost.

