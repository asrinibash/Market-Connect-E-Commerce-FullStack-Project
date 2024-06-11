# Market-Connect-E-Commerce-Web-Application

This is a full-stack eCommerce web application similar to Flipkart, developed using React, Spring Boot, MySQL, and Tailwind CSS.

**[Live Demo](https://asrinibash-marketconnect.netlify.app)**

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Features

- User Authentication and Authorization
- Product Listing and filtering
- Product Details Page
- Shopping Cart Management
- Order Placement and Tracking
- Admin Dashboard for Product Management
- Responsive Design

## Technologies Used

- **Frontend:** React, Tailwind CSS
- **Backend:** Spring Boot
- **Database:** MySQL

## Installation

### Prerequisites

- Node.js (v14 or higher)
- npm or yarn
- Java Development Kit (JDK 11 or higher)
- MySQL Server

### Backend Setup

1. Clone the repository:

    ```bash
    git clone git@github.com:asrinibash/Market-Connect-E-Commerce-FullStack-Project.git
    cd ecommerce-webapp
    ```

2. Navigate to the backend directory and build the Spring Boot application:

    ```bash
    cd backend
    ./mvnw clean install
    ```

3. Create a MySQL database and update the `application.properties` file with your database credentials:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
    spring.datasource.username=root
    spring.datasource.password=srinibash@123
    ```

4. Run the Spring Boot application:

    ```bash
    ./mvnw spring-boot:run
    ```

### Frontend Setup

1. Navigate to the frontend directory:

    ```bash
    cd ../frontend
    ```

2. Install the dependencies:

    ```bash
    npm install
    ```

    or

    ```bash
    yarn install
    ```

3. Start the React application:

    ```bash
    npm start
    ```

    or

    ```bash
    yarn start
    ```

## Usage

- Open your browser and navigate to `http://localhost:3000` to view the application.
- Use the admin dashboard to manage products.
- Explore the product catalog, add items to the cart, and proceed with checkout.

## Project Structure


## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure your code adheres to the project's coding standards and includes appropriate tests.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a pull request

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

