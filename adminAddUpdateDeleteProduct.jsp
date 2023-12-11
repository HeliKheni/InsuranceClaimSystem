<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<link rel="stylesheet" type="text/css" href="css/adminProduct.css">
</head>
<body>
<%@ include file="adminHeader.jsp" %>
<c:if test="${not empty sessionScope.username}">
	
		<%-- Display Edit Product Form --%>
        <c:if test="${not empty editProductDetails}">
               <form action="adminUpdateProductSubmitController" method="post" class="containerRe frm">
               <h3 style="color:black;margin-left:100px">Edit Product</h3><br />
			        <div class="form-group">
			            <label for="productSerialNo">Product Serial No:</label>
			            <input type="text" id="productSerialNo" name="productSerialNo" value="${editProductDetails.productSerialNo}" readonly>               
			        </div>
			        <div class="form-group">
			            <label for="productName">Product Name:</label>
			            <input type="text" id="productName" name="productName" value="${editProductDetails.productName}">
			        </div>
			        <div class="form-group">
			            <label for="model">Model:</label>
			            <input type="text" id="model" name="model" value="${editProductDetails.model}">
			        </div>
			        <div class="form-group">
			            <label for="price">Price:</label>
			            <input type="text" id="price" name="price" value="${editProductDetails.price}">
			        </div>
			        <div class="form-group">
			            <label for="manufacturer">Manufacturer:</label>
			            <input type="text" id="manufacturer" name="manufacturer" value="${editProductDetails.manufacturer}">
			        </div>
			        <div class="form-group">
			            <label for="description">Description:</label>
			            <textarea id="description" name="description" rows="4" cols="50">${editProductDetails.description}</textarea>
			        </div>
			        <div class="form-group">
			            <label for="color">Color:</label>
			            <input type="text" id="color" name="color" value="${editProductDetails.color}">
			        </div>
			        
			        <input type="submit" name="editProductSubmit" value="Edit Product" style="margin-left:100px">
			        
					<%-- Display error message if any --%>
				    <c:if test="${not empty error}">
				        <p style="color: red;">${error}</p>
				    </c:if>			
				    <%-- Display success message if product added successfully --%>
				    <c:if test="${not empty success}">
				        <p style="color: green;">${success}</p>
				    </c:if>
		    	</form>
        </c:if>  
         
        <%-- Display Add Product Form --%>
        <c:if test="${empty editProductDetails}">
            <form action="adminAddUpdateDeleteProductController" method="post" class="containerRe frm">
            <h3 style="color:black;margin-left:100px">Add Product</h3><br />
                <div class="form-group">
	            <label for="productSerialNo">Product Serial No:</label>
	            <input type="text" id="productSerialNo" name="productSerialNo">
	        </div>
	        <div class="form-group">
	            <label for="productName">Product Name:</label>
	            <input type="text" id="productName" name="productName">
	        </div>
	        <div class="form-group">
	            <label for="model">Model:</label>
	            <input type="text" id="model" name="model">
	        </div>
	        <div class="form-group">
	            <label for="price">Price:</label>
	            <input type="text" id="price" name="price">
	        </div>
	        <div class="form-group">
	            <label for="manufacturer">Manufacturer:</label>
	            <input type="text" id="manufacturer" name="manufacturer">
	        </div>
	        <div class="form-group">
	            <label for="description">Description:</label>
	            <textarea id="description" name="description" rows="4" cols="50"></textarea>
	        </div>
	        <div class="form-group">
	            <label for="color">Color:</label>
	            <input type="text" id="color" name="color">
	        </div>
	        <input type="submit" value="Add Product" style="margin-left:100px">
	        

            </form>
            	        <%-- Display error message if any --%>
		    <c:if test="${not empty error}">
		        <p style="color: red;" align="center">${error}</p>
		    </c:if>
		
		    <%-- Display success message if product added successfully --%>
		    <c:if test="${not empty success}">
		        <p style="color: green;" align="center">${success}</p>
		    </c:if>     
        </c:if>

<%-- Table for product details (only visible during adding a product) --%>
<c:if test="${not empty allProductDetails}">
    <table border="1" class="containerRe">
        <thead>
            <tr>
                <th>Product Serial No</th>
                <th>Product Name</th>
                <th>Model</th>
                <th>Price</th>
                <th>Manufacturer</th>
                <th>Description</th>
                <th>Color</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${allProductDetails}">
                <tr>
                    <td>${product.productSerialNo}</td>
                    <td>${product.productName}</td>
                    <td>${product.model}</td>
                    <td>${product.price}</td>
                    <td>${product.manufacturer}</td>
                    <td>${product.description}</td>
                    <td>${product.color}</td>
                    <td>
                        <p><!-- Edit button form -->
                        <form action="adminUpdateProductController" method="post" style="display: inline;">
                            <input type="hidden" name="editProduct" value="true">
                            <input type="hidden" name="productSerialNo" value="${product.productSerialNo}">
                            <button type="submit" class="edit-button">Edit</button>
                        </form> 
                         <!-- Delete button form -->
				        <form action="adminDeleteProductController" method="post" style="display: inline;">
				            <input type="hidden" name="deleteProduct" value="true">
				            <input type="hidden" name="productSerialNo" value="${product.productSerialNo}">
				            <button type="submit" class="delete-button">Delete</button>
				        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>
</c:if>
</body>
</html>