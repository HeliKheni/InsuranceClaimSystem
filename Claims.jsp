<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
     <title>Register Claim</title>
    <link rel="stylesheet" type="text/css" href="css/registerProduct.css">
 <script>
        // JavaScript function to set today's date in the "Date of Claim" field
        function setTodayDate() {
            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
            var yyyy = today.getFullYear();

            var todayFormatted = yyyy + '-' + mm + '-' + dd;
            document.getElementById('dateOfClaim').value = todayFormatted;
        }
    </script>
</head>
<body onload="setTodayDate()">
<%@ include file="header.jsp" %>
	<c:if test="${ not empty sessionScope.username}">
    <main>
       <div class="containerRe">
        <h2>Register Claim</h2>
        <!-- Display errors if any -->
                <c:if test="${not empty error}">
                    <div style="color: red;">
                        ${requestScope.error}
                    </div>
                </c:if>  
                      
       <form action="addClaimSubmitController" method="post" class="claimform">
		    <div class="form-group">
		        <label for="productName">Product Name:</label>
		        <select id="productName" name="productName" required>
		            <c:forEach var="product" items="${userProducts}">
		                <option value="${product.productName}">${product.productName}</option>
		            </c:forEach>
		        </select>
		    </div>
		 	<div class="form-group">
			    <label for="dateOfClaim">Date of Claim:</label>
			      <input type="date" id="dateOfClaim" name="dateOfClaim" readonly>
             </div>

	 		<div class="form-group">
		        <label for="description">Description of Incident:</label>
		        <textarea id="description" name="description" required></textarea>
		     </div>

		
		   <input type="submit" value="Register Claim">
		 
		</form>
	  <c:if test="${not empty sucess}">
                    <div style="color: green;">
                        ${requestScope.sucess}
                    </div>
                </c:if><br />

      <!-- Display registered products and associated claims by the user -->
            <h3>Your Registered Products:</h3>
             <table>
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Purchase Date</th>
                            <th>Claims (DateofClaim, Description, ApprovalStatus)</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="registeredProduct" items="${userProducts}">
               
                        <tr>
                        	<td> ${registeredProduct.productName} </td>
                            <td>${registeredProduct.purchasedDate}</td>
                            <td>
                                <c:forEach var="claim" items="${registeredProduct.claims}">
                                    ${claim.dateOfClaim} - ${claim.descriptionOfIncident} - ${claim.approvalStatus}<br />
                                </c:forEach>
                            </td>
                        </tr>
                         </c:forEach>
                    </tbody>
                </table>
           

    </div>
    </main>
    </c:if>
</body>
</html>