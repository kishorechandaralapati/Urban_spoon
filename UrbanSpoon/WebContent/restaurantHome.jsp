<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="css/Main.css">
<title>Insert title here</title>
<script type="text/javascript">
function  addBranch () {document
	.getElementById("AddBranch").style.display = "block";document
	.getElementById("AddRecipe").style.display = "none";document
	.getElementById("AddCusine").style.display = "none";
	
}

function restaurantform () {document
	.getElementById("AddBarnch").style.display = "none";document
	.getElementById("AddRecipe").style.display = "block";document
	.getElementById("AddCusine").style.display = "none";
	
}

function restaurantform () {document
	.getElementById("AddBarnch").style.display = "none";document
	.getElementById("AddRecipe").style.display = "block";document
	.getElementById("AddCusine").style.display = "none";
	
}

function restaurantform () {document
	.getElementById("AddBarnch").style.display = "none";document
	.getElementById("AddRecipe").style.display = "block";document
	.getElementById("AddCusine").style.display = "none";
	
}
</script>
<style type="text/css">
#container {
	height: 115%;;
}

#box {
	width: 18%;
	background-color: aquamarine;
	border-radius: 5px;
	padding: 5px;
	margin: 3px 0px 0px 15px;
	float: right;
}

img {
	border-radius: 2px;
	width: 70%;
}

#display {
	width: 80%;
	float: right;
	margin-top: -57%;
}

#nav-links {
	width: 20%;
	height: 100%;
	background-color: red;
}

#links {
	position: absolute;
	margin-top: 70px;
	margin-left: 43px;
}
</style>
</head>
<body>
	<div>
		<jsp:include page="header.jsp"></jsp:include>
	</div>

	<div id="container">
		<div id="nav-links">
			<div id="links">
				<a href="RestaurantController?action=addBranch">Add Branch</a> <br></br>
				<a href="RestaurantController?action=adddCuisine">AddCuisine</a> <br>
				<br> <a href="RestaurantController?action=addRecipe">Add
					Recipe</a><br> <br> <br> <a
					href="RestaurantController?action=viewCusine">View Cuisine</a><br>
				<br> <br> <a
					href="RestaurantController?action=viewBranch">View Branches</a><br>
				<br> <br> <a
					href="RestaurantController?action=viewRecipe">View Recipes</a>
			</div>


		</div>

		<input type="submit" value="Submit">



	</div>

	<div>
		<jsp:include page="footer.html"></jsp:include>

	</div>
</body>
</html>