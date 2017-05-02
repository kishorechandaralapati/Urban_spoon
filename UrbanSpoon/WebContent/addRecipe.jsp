<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/Main.css">

<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div id="container">
		<div align="center" id="subcontainer">
			<form action="RestaurantController" id="restautrant" method="post" enctype="multipart/form-data">
				<h3>Add Recipe</h3>
				<fieldset id="fields">
				<input type="hidden" name="action" value="addRecipe">
					<label for="name of item">Name of Item:</label><input type="text"
						name="name"> <label for="Description">Description:</label><input
						type="text" name="description"> <label for="image">Image:</label><input
						type="file" name="image" > <label>Cusine</label><select
						name="cuisine">
						<c:forEach items="${cuisineList}" var="cuisine">
							<option value="${cuisine.cuisineId}"><c:out
									value="${cuisine.name}"></c:out></option>
						</c:forEach>
					
					</select><br><br> <label>Branches</label><select name="branch" multiple="multiple">
						<c:forEach items="${branchList}" var="branches">
							<option value="${branches.branchId}"><c:out
									value="${branches.location}"></c:out></option>
						</c:forEach>
						
					</select>
					<input type="text" name="price">  
					<input type="submit" value="Submit">
				</fieldset>
			</form>

		</div>
	</div>

	<jsp:include page="footer.html"></jsp:include>
</body>
</html>