<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<style type="text/css">
#container{
height:70%;

}


</style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div id="container">
<label>Branches</label><select name="branch" multiple="multiple">
						<c:forEach items="${branchList}" var="branches">
							<option value="${branches.branchId}"><c:out
									value="${branches.location}"></c:out></option>
						</c:forEach>
						
					</select>
<label>Recipe</label><select name=recipe>
<c:forEach items="${recipeList}" var="recipe">
<option value="${recipe.recipeId}"><c:out value="${name}"></c:out></option>
</c:forEach>
</select>

</div>

<jsp:include page="footer.html"></jsp:include>
</body>
</html>