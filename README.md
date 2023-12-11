# InsuranceClaimSystem

## Overview
Welcome to the ABC Company Customer Service (Example company name) web application repository. This web application is designed to streamline customer interactions and provide efficient management tools for administrators. Below, you will find information about the database design, web forms, functionalities, security features, and administrator capabilities.

## Database Design
The MySQL database is structured to efficiently store user information, product details, claims, and administrator-related data.

## Technologies Used
- J2EE (Java 2 Platform, Enterprise Edition)
- JSP (JavaServer Pages)
- MySQL Database
- Apache Tomcat
- JDBC (Java Database Connectivity)
- HTML/CSS
- Java Servlets
- JavaScript
- MySQL Workbench
- MVC Architecture
- Security Measures (Form Validation, Access Controls, Encryption)
- Feedback Functionality (JSP and Java Servlets)

## Web Forms
User-friendly forms have been implemented for account creation, product registration, claim submission, and feedback. These forms include proper validation to ensure data accuracy.

## Functionality
1. **User Registration and Product Registration:**
   - Users can create accounts with necessary details.
   - Multiple products can be registered under a user account.

2. **Claim Submission:**
   - Users can submit claims for registered devices.
   - A user can claim repair/replacement up to 3 times within five years.

3. **Claim Status Checking:**
   - Users can check the status of their claims and view claim history.

4. **Security Features:**
   - Form validation ensures data accuracy.
   - Only valid users can register products.

## Administrator Functionalities
Admin users have additional functionalities:

1. **User and Product Management:**
   - Admins can manage user accounts and view all registered products.

2. **Claim Management:**
   - Admins can view recent claims and update their status.

3. **Reports:**
   - Admins can generate reports on users, registered products, and claims.

## Setup Instructions
1. **Database Setup:**
   - Use MySQL Workbench to create the required database and tables.

2. **Web Application Setup:**
   - Deploy the web application on a J2EE server (e.g., Apache Tomcat).
   - Configure the database connection string and credentials.

3. **Run the Application:**
   - Access the web application through the provided URL.
   - Users and admins can perform their respective tasks.

4. **Additional Feature Implementation:**
   - Implement the feedback functionality as described in the project requirements.

## Additional Feature
**Feedback Functionality:**
   - Users can provide feedback on the service.
   - Feedback data is stored in a dedicated table.
   - Admins can view and manage user feedback.
     
## Contributors
- Heli Kheni
- Vrushali Ponkia

## Conclusion
This web application enhances customer service processes for ABC Company, offering a user-friendly interface for customers and robust management tools for administrators. It prioritizes data security, integrity, and a seamless user experience.

## Acknowledgments
This project was developed as part of academic coursework and is intended for educational purposes.

