<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/Main.css">
<style type="text/css">
#container {
	height: 115%;;
}

.col-sm-3 {
	width: 18%;
	background-color: aquamarine;
	border-radius: 5px;
	padding: 5px;
	margin: 3px 0px 0px 15px;
}

img {
	border-radius: 2px;
	width: 70%;
}
</style>
</head>
<body>
	<div>
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<div id="container" class="row">

<c:forEach items="${restuarntList}" var="restaurant">
   <h4>${restaurant.registrationName}</h4>
            	<div class="col-sm-3">
		<c:forEach items="${restaurant. branchesList}" var="branch">
		
				
				<a href=""><img alt="" src="images/branches/${branch.imagePath}"></a>
				<h5>
					<c:out value="${branch.location}"></c:out>
				</h5>
				<p>${branch.phoneNumber}</p>
				<p>${branch.emailId}</p>
				<div></div>
			
		</c:forEach>
		</div>
</c:forEach>

	</div>

	<div>
		<jsp:include page="footer.html"></jsp:include>

	</div>
</body>
</html>