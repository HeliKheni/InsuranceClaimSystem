<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>ABC Insurance Company</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .header {
            background-color: #3498db;
            color: #fff;
            text-align: center;
            height: 60px;
            padding: 10px;
        }

        .about-us-section {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .about-us-section h3 {
            color: #3498db;
        }

        .about-us-section h4 {
            color: #2c3e50;
        }

        .about-us-section ul {
            list-style-type: none;
            padding: 0;
        }

        .about-us-section li {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>
    <c:if test="${not empty sessionScope.username}">
        <main>
            <!-- About Us Section -->
            <div class="about-us-section">
                <h3>About Us</h3>
                <p>
                    ABC Insurance Company is a leading provider of protection plans for your digital devices.
                    Our services are designed to offer comprehensive coverage, ensuring that your devices are
                    safeguarded against unforeseen incidents. With a focus on customer satisfaction and efficient
                    claim processing, we strive to provide a seamless experience for our valued customers.
                </p>

                <!-- Services Offered -->
                <h4>Our Services</h4>
                <ul>
                    <li>Register Devices for protection</li>
                    <li>Do up to 3 claims of repair/replace for each product</li>
                    <li>Use protection plan for 5 years</li>
                    <li>Check status of all claims</li>
                </ul>
            </div>
        </main>
    </c:if>
</body>
</html>
