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
            <table>
                <thead>
                    <tr>
                    <th>User Name</th>
                        <th>Product Name</th>
	            		<th>Comments</th>
	            		<th>Rating</th>
	            <th>Submission Date</th>
                    </tr>
                </thead>
                <tbody>
              		 <c:choose>
                 <c:when test="${empty param.search}">       	
				        <c:forEach var="feedback" items="${feedbackList}">
				            <tr>
				            <td>${feedback.username}</td>
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
						             	 <td>${feedback.username}</td>
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
    </c:if>
</body>
</html>