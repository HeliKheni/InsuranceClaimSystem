<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
     <title>Register Product</title>
    <link rel="stylesheet" type="text/css" href="css/registerProduct.css">
</head>
<body>
<%@ include file="header.jsp" %>
	<c:if test="${ not empty sessionScope.username}">
    <main>
       <div class="containerRe">
        <h2>Register Product</h2>
        <!-- Display errors if any -->
                <c:if test="${not empty error}">
                    <div style="color: red;">
                        ${requestScope.error}
                    </div>
                </c:if>  
                      
       <form action="registerProductSubmitController" method="post">
		    <div class="form-group">
		        <label for="productName">Product Name:</label>
		        <select id="productName" name="productName" required>
		            <c:forEach var="product" items="${productList}">
		                <option value="${product.productName}">${product.productName}</option>
		            </c:forEach>
		        </select>
		    </div>
		
		    <div class="form-group">
		        <label for="purchaseDate">Purchase Date:</label>
		        <input type="date" id="purchaseDate" name="purchaseDate" required>
		    </div>
		
		    <input type="submit" value="Register Product">
		
		</form>
	   <c:if test="${not empty success}">
                    <div style="color: green;">
                        ${requestScope.success}
                    </div>
                </c:if>

       <!-- Display registered products by the user -->
	<h3>Your Registered Products:</h3>
	<table class="reg">
	    <thead class="reg">
	        <tr class="reg">
	            <th class="reg">Product Name</th>
	            <th class="reg">Purchase Date</th>
	        </tr>
	    </thead>
	    <tbody class="reg">
	        <c:forEach var="registeredProduct" items="${userProducts}">
	            <tr>
	                <td>${registeredProduct.productName}</td>
	                <td>${registeredProduct.purchasedDate}</td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>

    </div>
    </main>
    </c:if>
</body>
</html>