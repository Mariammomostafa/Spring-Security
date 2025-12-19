<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1> Login Form </h1>


	<form:form action="/springMvc/login-processing" method="post">
  
 Username :  <input type="text" name="username" />
		<br>
		<br>
 Password :  <input type="password" name="password" />
		<br>
		<br>

		<input type="submit" value="login" />

	</form:form>

<br>
  
  
  
</body>
</html>