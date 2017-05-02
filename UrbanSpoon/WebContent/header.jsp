<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<style type="text/css">
body {
	margin: 0px;
}

#header {
	background-color: #03A9F4;;
	height: 15%;
	width:100%;
}

ul, li {
	float: left;
	display: inline;
	margin: -10PX 0PX 0PX 10PX;
}

a {
	padding: 8px;
}

#right {
	float: right;
	margin: 40px 0px 0px 8px;
	position: static;
}

input[type="search"] {
	width: 35%;
	border-radius: 10px;
	height: 6%;
	border-color: orange;
	margin: 2% 0% 0% 34%;
	position: absolute;
}

#logo img {
	width: 5%;
	position: absolute;
	border-radius: 12px;
}

input[type="submit"] {
	/* height: 5%;
	border-radius: 7px;
	position: absolute; */
}
</style>
</head>
	<div id="header">
		<div id="logo">
			<img alt="" src="images/UrbanSpoon.jpg">
		</div>
		<div>
			<input type="search" name="search" placeholder="search for item">
			<input type="submit" name="search" value="search">
		</div>

		<div id="right">
			<ul>
				<c:if test="${ loggedUser eq null }">
					<li><a href="HomeController">Home</a></li>
					<li><a href="registration.jsp">Register</a></li>
					<li><a href="login.jsp">Login</a></li>
				</c:if>
				<c:if test="${ loggedUser ne null }">
					<p>Welcome ${ loggedUser }</p>
					<li><a href="HomeController?action=logout">Logout</a></li>
				</c:if>
			</ul>
		</div>

	</div>
