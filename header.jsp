<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ABC Insurance Company</title>
    <%@ include file="checkSession.jsp" %>
    <link rel="stylesheet" type="text/css" href="css/header.css">
</head>
<body>
	<c:if test="${not empty sessionScope.username}">
    <header>
        <div class="containerNav">
            <h3>Welcome ${sessionScope.username}! in ABC Insurance Company </h3>
        </div>
    </header>
    <nav>
        <div class="containerNav">
            <ul>
             <li><a href="Home.jsp">Home</a></li>
                <li><a href="registerProductController">Register Product</a></li>
                <li><a href="addClaimController">Claim</a></li>
                <li><a href="feedbackController">Feedback</a></li>
               <li><a href="logoutController">Logout</a></li>
            </ul>
        </div>
    </nav>
    <!-- Rest of content -->
    </c:if>
</body>
</html>

