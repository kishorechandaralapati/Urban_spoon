<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
		<div align="center" id="register">
			<form action="HomeController" id="restaurant" method="post" >
				<fieldset id="fields">
				<input type="hidden" name="action" value="login"> 
					<label>Name</label> <input type="text" name="userName" value="${userName}"> <label>Password</label>
					<input type="password" name="password"> <label></label>
					<div>
						User <input type="radio" name="loginAs" value="userLogin" style="width: 5%;"> 
						Restaurant Owner <input type="radio" name="loginAs" value="restaurantLogin" style="width: 5%;">
					</div>
					<input type="submit" value="submit">

				</fieldset>

			</form>
		</div>

	</div>


	<jsp:include page="footer.html"></jsp:include>
</body>
</html>