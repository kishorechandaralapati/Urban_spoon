<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
#container {
	height: 75%;
}

#subcontainer {
	width: 30%;
	height: 75%;
	background-color: orange;
	border-width: 2px;
	margin: 0px auto;
	position: absolute;
	margin-left: 500px;
	margin-top: -16%;
}

label {
	display: inline-block;
	width: 90px;
	margin-right: 30px;
	text-align: right;
}

#fields input {
	width: 50%;
	height: 35px;
	border-radius: 6px;
	padding: 0px 13px 4px 23px;
	margin-bottom: 27px;
}
</style>
</head>



<body>
	<jsp:include page="header.jsp" />

	<div id="container">

		<div id="nav-links">
			<div id="links">
				<a href="RestaurantController?action=addbranch">Add Branch</a> <br></br> <a
					href="addCuisie.jsp">AddCuisine</a> <br> <br> <a
					href="addRecipe.jsp">Add Recipe</a><br> <br> <br> <a
					href="addcuisines.jsp">Add Cuisine</a><br> <br> <br>
				<a href="viewBranches.jsp">View Branches</a><br> <br> <br>
				<a href="viewItems.jsp">View Recipes</a>
			</div>


		</div>



		<div align="center" id="subcontainer">
			<form action="RestaurantController" id="restaurant" method="post" enctype="multipart/form-data">
				<h2>Add Branch</h2>
				<fieldset id="fields">
				<input type="hidden" name="action" value="addbranch">
					<label>Location</label> <input type="text" name="location">
					<label>Email</label> <input type="email" name="emailId"> 
					<label>City</label> <input type="text" name="city"> 
					<label>Postal Code</label> <input type="text" name="postalCode"> 
					<label>State</label> <input type="text" name="state"> 
					<label>Phone Number</label> <input type="number" name="phoneNumber"> 
					<label>Country</label> <input type="text" name="country"> 
					<label>Image </label> <input type="file" accept="image/*" name="image"> 
					<input type="submit" value="ADD">
				</fieldset>
			</form>

		</div>
	</div>


	<div>
		<jsp:include page="footer.html"></jsp:include>
	</div>
</body>
</html>
