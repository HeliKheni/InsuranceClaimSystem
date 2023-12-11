<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
	
<%@ include file="adminHeader.jsp" %>	
<c:if test="${not empty sessionScope.username}">
        <main>
            <div class="about-us-section">
                <p>
                    Manage the operations and functionalities of ABC Insurance Company here.
                </p>
                <h4>As an admin, you have access to various management options:</h4>
                <ul>
                    <li>Manage user accounts, permissions, and details.</li>
                    <li>View and manage registered products.</li>
                    <li>Add, edit, or remove insurance products.</li>
                    <li>Handle insurance claims and related tasks.</li>
                </ul>
            </div>
        </main>
</c:if>

</body>
</html>