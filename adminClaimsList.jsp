<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Claims List</title>
<link rel="stylesheet" type="text/css" href="css/admin.css">

</head>
<body>
    <%@ include file="adminHeader.jsp" %>
    <c:if test="${not empty sessionScope.username}">
    
        <div class="containerRe">
        	<!-- Search bar -->
                <form action="" method="get" class="search-form">
                    <input type="text" name="search" placeholder="Search" class="search-input">
                    <input type="submit" value="Search" class="submit-button">
                </form>
            <table id="registeredProductsTable">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Product Name</th>
                        <th>Purchased Date</th>
                        <th>Date of Claim</th>
                        <th>Description of Incident</th>
                        <th>Approval Status</th>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                 <c:when test="${empty param.search}">
                    	<c:forEach var="claim" items="${claimDetails}">
                    	<tr>
	                            <td>${claim.username}</td>
	                            <td>${claim.productName}</td>
	                            <td>${claim.purchasedDate}</td>
	                            <td>${claim.dateOfClaim}</td>
	                            <td>${claim.descriptionOfIncident}</td>
	                            <c:if test="${claim.approvalStatus eq 'Pending'}">
									    <!-- Display buttons when the status is Pending -->
									    <td class="actions-column">
									        <form action="adminClaimStatusSubmitController" method="post" style="display: inline;"> 
									            <input type="hidden" name="claimId" value="${claim.claimId}" /> 
									            <input type="submit" name="action" value="approve" class="approve-button" />
									            <input type="submit" name="action" value="reject" class="reject-button" />
									        </form>
									    </td>
									</c:if>
									<c:if test="${claim.approvalStatus ne 'Pending'}">
									    <!-- Display status when the status is not Pending -->
									    <td>${claim.approvalStatus}</td>
									</c:if>
	                            
	                           
	                        </tr>
                       
                    </c:forEach>
                     </c:when>
						    <c:otherwise>
						    <c:forEach var="claim" items="${claimDetails}">
                    	  <c:if test="${(fn:containsIgnoreCase(claim.username, param.search) 
						                            or fn:containsIgnoreCase(claim.productName, param.search)
						                            or fn:containsIgnoreCase(claim.purchasedDate, param.search)
						                            or fn:containsIgnoreCase(claim.dateOfClaim, param.search)
						                            or fn:containsIgnoreCase(claim.descriptionOfIncident, param.search)
						                            or fn:containsIgnoreCase(claim.approvalStatus, param.search))
						                            and user.username ne sessionScope.username}">
						                <tr>
				                            <td>${claim.username}</td>
				                            <td>${claim.productName}</td>
				                            <td>${claim.purchasedDate}</td>
				                            <td>${claim.dateOfClaim}</td>
				                            <td>${claim.descriptionOfIncident}</td>
				                            <c:if test="${claim.approvalStatus eq 'Pending'}">
												    <!-- Display buttons when the status is Pending -->
												    <td class="actions-column">
												        <form action="adminClaimStatusSubmitController" method="post" style="display: inline;"> 
												            <input type="hidden" name="claimId" value="${claim.claimId}" /> 
												            <input type="submit" name="action" value="approve" class="approve-button" />
												            <input type="submit" name="action" value="reject" class="reject-button" />
												        </form>
												    </td>
												</c:if>
												<c:if test="${claim.approvalStatus ne 'Pending'}">
												    <!-- Display status when the status is not Pending -->
												    <td>${claim.approvalStatus}</td>
												</c:if>
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