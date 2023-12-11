<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>ABC Insurance Company</title>
	<%@ include file="checkSession.jsp" %>
	<link rel="stylesheet" type="text/css" href="css/header.css">
</head>
<body>

<c:if test="${not empty sessionScope.username}">
    <header>
        <div class="containerNav">
            <h3>Welcome ${sessionScope.username} in ABC Insurance Company</h3>
        </div>
    </header>
    <nav>
        <div class="containerNav">
            <ul>
            	<li><a href="adminHome.jsp">Home</a></li>
                <li><a href="adminUserListController">User List</a></li>
                <li><a href="adminRegisteredProductListController">Registered Product</a></li>
                <li><a href="adminAddUpdateDeleteProductController">Manage Product</a></li>
                <li><a href="adminClaimsListController">Claims</a></li>
                <li><a href="adminFeedbackController">Feedback</a></li>
                <li><a href="logoutController">Logout</a></li>
            </ul>
        </div>
    </nav>
    <!-- Rest of content -->
</c:if>

</body>
</html>