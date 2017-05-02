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
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/Main.css">
<style type="text/css">
body{
margin: 0;
    padding: 0;
    overflow-x: hidden;
    background: #F3F3F3;;
    min-width: 320px;
    font-family: 'Source Sans Pro','Helvetica Neue',Helvetica,Arial,sans-serif;
    font-size: 14px;
    line-height: 1.4285em;
    color: #33373D;


}
#container {
	height: 115%;;
}

.col-sm-3 {
	width: 18%;
	background-color: #ffffff;
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



		<c:forEach items="${recipesList}" var="recipe">
			<div  class="col-sm-3">
				
				<a href="HomeController?action=viewRestaurantviseBranches&id=<c:out value="${recipe.recipeId}"/>"><img alt=""
					src="images/recipes/${recipe.imagePath}"></a>
					<h5>
					<c:out value="${recipe.name}"></c:out>
				</h5>
				<p>
					${recipe.description}					
				</p>
				<div>
				
				</div>
			</div>
		</c:forEach>
		

	</div>

	<div>
		<jsp:include page="footer.html"></jsp:include>

	</div>
</body>
</html>