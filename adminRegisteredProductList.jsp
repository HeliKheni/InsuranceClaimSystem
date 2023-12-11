<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>Registered Product</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/admin.css">
	<script>
   /* $(document).ready(function() {
        var previousUsername = null;

        $('table tbody tr').each(function() {
            var usernameCell = $(this).find('td:first-child');
            var currentUsername = usernameCell.text().trim();

            if (currentUsername === previousUsername) {
                usernameCell.css('visibility', 'hidden');
            } else {
                previousUsername = currentUsername;
            }
        });
    }); */
</script>
	<style>
		  
</style>
</head>
<body>
<%@ include file="adminHeader.jsp" %>
    <c:if test="${not empty sessionScope.username}">
        <div class="containerRe">
            <form action="" method="get" class="search-form">
                <input type="text" name="search" placeholder="Search" class="search-input">
                <input type="submit" value="Search" class="submit-button">
            </form>
            <table>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Product Name</th>                     
                        <th>Purchased Date</th>                    
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty param.search}">
                            <!-- If 'search' parameter is not provided or empty -->
                            <c:forEach var="userProduct" items="${userProductDetails}">
                                <tr>
                                    <td>${userProduct.username}</td>
                                    <td>${userProduct.productName}</td>
                                    <td>${userProduct.purchasedDate}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <!-- If 'search' parameter is provided -->
                            <c:forEach var="userProduct" items="${userProductDetails}">
                                <c:if test="${fn:containsIgnoreCase(userProduct.username, param.search) || fn:containsIgnoreCase(userProduct.productName, param.search)}">
                                
                                    <tr>
                                        <td>${userProduct.username}</td>
                                        <td>${userProduct.productName}</td>
                                        <td>${userProduct.purchasedDate}</td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </c:if>
</body>
</html>