<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <h2>Add Feedback</h2>
       <!-- Display errors if any -->
                <c:if test="${not empty error}">
                    <div style="color: red;">
                        ${requestScope.error}
                    </div>
                </c:if>  
                      
       <form action="feedbackSubmitController" method="post">
        <div class="form-group">
		        <label for="productName">Product Name:</label>
		        <select id="productName" name="productName" required>
		            <c:forEach var="product" items="${userProducts}">
		                <option value="${product.productName}">${product.productName}</option>
		            </c:forEach>
		        </select>
		    </div>
		    <div class="form-group">
			    <label for="comment">Your Feedback:</label>
	        	<textarea id="comment" name="comment" rows="4" cols="50" required></textarea><br>
		    </div>
		    <div class="form-group">
		        <label for="rating">Rating (1-5):</label>
        		<input type="number" id="rating" name="rating" min="1" max="5" required><br>
		    </div>
        	<input type="submit" value="Submit Feedback">
		</form>
	   <c:if test="${not empty success}">
                    <div style="color: green;">
                        ${requestScope.success}
                    </div>
       </c:if>

       <!-- Display Feedback by the user --><br />
	<h3 style="color:black">Your Feedback:</h3>
	  <!-- Search bar -->
                <form action="" method="get" class="search-form">
                    <input type="text" name="search" placeholder="Search" class="search-input" style="width: auto; padding: 10px; border: 1px solid black; border-radius: 0; font-size: 1em;">
					<input type="submit" value="Search" style="width: auto; padding: 10px; border: none; border-radius: 0; font-size: 1em;">
                </form>
       
	<table class="reg">
	    <thead class="reg">
	        <tr class="reg">
	        	
	            <th class="reg">Product Name</th>
	            <th class="reg">Comments</th>
	            <th class="reg">Rating</th>
	            <th class="reg">Submission Date</th>
	        </tr>
	    </thead>
	    
	    <tbody class="reg">
	    <c:choose>
                 <c:when test="${empty param.search}">       	
				        <c:forEach var="feedback" items="${feedbackList}">
				            <tr>
				                <td>${feedback.product_serial_no}</td>
				                 <td>${feedback.comment}</td>
				                  <td>${feedback.rating}</td>
				                <td>${feedback.submissionDate}</td>
				            </tr>
				        </c:forEach>
                     </c:when>
				<c:otherwise>
						<c:forEach var="feedback" items="${feedbackList}">
						           <c:if test="${(fn:containsIgnoreCase(feedback.product_serial_no, param.search)
								                            or fn:containsIgnoreCase(feedback.comment, param.search)
								                             or fn:contains(feedback.rating, param.search)
								                            or fn:contains(feedback.submissionDate, param.search))
								                           and user.username ne sessionScope.username}">
								          <tr>
						             	
						                <td>${feedback.product_serial_no}</td>
						                 <td>${feedback.comment}</td>
						                  <td>${feedback.rating}</td>
						                <td>${feedback.submissionDate}</td>
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