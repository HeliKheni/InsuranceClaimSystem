<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error - ABC Insurance Company</title>
    <link rel="stylesheet" type="text/css" href="css/error.css">
</head>
<body>
	<c:if test="${empty sessionScope.username}">
    <div class="containerError">
        <h1>Error 404</h1>
        <p>You are not allowed to access this page. Please <a href="login.jsp">log in</a> first.</p>
    </div>
    </c:if>
</body>
</html>