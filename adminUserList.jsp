<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Information</title>a
    <link rel="stylesheet" type="text/css" href="css/admin.css">
  	
</head>
<body>
<%@ include file="adminHeader.jsp" %>
    <c:if test="${not empty sessionScope.username}">
        <main>
            <div class="containerRe">
                <h3>User Information</h3>
                
				<!-- Search bar -->
                <form action="" method="get" class="search-form">
                    <input type="text" name="search" placeholder="Search by User Details" class="search-input">
                    <input type="submit" value="Search" class="submit-button">
                </form>
				<table id="userTable">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Phone No</th>
                            <th>Email</th>
                            <th>Address</th>
                        </tr>
                    </thead>
                    <tbody>         
                       <c:choose>
						    <c:when test="${empty param.search}">
						        <c:forEach var="user" items="${userList}">
						            <c:if test="${user.username ne sessionScope.username}">
						                <c:choose>
						                    <c:when test="${fn:containsIgnoreCase(user.username, param.search) 
						                                    or fn:containsIgnoreCase(user.cellphoneNo, param.search)
						                                    or fn:containsIgnoreCase(user.email, param.search)
						                                    or fn:containsIgnoreCase(user.address, param.search)}">
						                        <tr>
						                            <td>${user.username}</td>
						                            <td>${user.cellphoneNo}</td>
						                            <td>${user.email}</td>
						                            <td>${user.address}</td>
						                        </tr>
						                    </c:when>
						                </c:choose>
						            </c:if>
						        </c:forEach>
						    </c:when>
						    <c:otherwise>
						        <c:forEach var="user" items="${userList}">
						            <c:if test="${(fn:containsIgnoreCase(user.username, param.search) 
						                            or fn:containsIgnoreCase(user.cellphoneNo, param.search)
						                            or fn:containsIgnoreCase(user.email, param.search)
						                            or fn:containsIgnoreCase(user.address, param.search))
						                            and user.username ne sessionScope.username}">
						                <tr>
						                    <td>${user.username}</td>
						                    <td>${user.cellphoneNo}</td>
						                    <td>${user.email}</td>
						                    <td>${user.address}</td>
						                </tr>
						            </c:if>
						        </c:forEach>
						    </c:otherwise>
						</c:choose> 
                    </tbody>
                </table>
            </div>
        </main>
    </c:if>
</body>
</html>