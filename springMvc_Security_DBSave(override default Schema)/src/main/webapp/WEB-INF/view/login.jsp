<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>

	<c:if test="${param.error != null}">
		
		<i>Invalid username & password</i>

	</c:if>
	
	<c:if test="${param.logout != null}">
		
		<i>You are Successfully logged out .....</i>

	</c:if>
	<h1>My Custom Login Form page</h1>


	<form:form action="/springMvc_NoXml/login-processing" method="post">
  
 Username :  <input type="text" name="username" />
		<br>
		<br>
 Password :  <input type="password" name="password" />
		<br>
		<br>

		<input type="submit" value="login" />

	</form:form>
	<br>
	<br>

	
	<a href="/springMvc_NoXml/register">Register first</a>


</body>
</html>