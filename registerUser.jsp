<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input,
        textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 12px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: #fff;
            border: none;
            padding: 10px;
            cursor: pointer;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
	<body>

    <form action="registerUserController" method="post">
        <h3>Register User</h3>

        <%-- Display error message if error parameter is present --%>
        <c:if test="${not empty param.error}">
            <p style="color: #ff0000;"><%= request.getParameter("error") %></p>
        </c:if>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required pattern=".{4,}" title="Username must be at least 4 characters"><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required pattern=".{4,}" title="Password must be at least 4 characters"><br>

        <label for="cellphoneNo">Phone No:</label>
        <input type="tel" id="cellphoneNo" name="cellphoneNo" required pattern="[0-9]{10}" title="Enter a valid 10-digit phone number"><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="address">Address:</label>
        <textarea id="address" name="address" required></textarea><br>

        <input type="submit" value="Register">
        <input type="button" value="Login" onclick="window.location='login.jsp';">
    </form>
</body>
</html>
</body>
</html>
