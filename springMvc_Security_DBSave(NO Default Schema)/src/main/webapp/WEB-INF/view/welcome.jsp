<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>

<c:if test="${param.error != null}">
		
		<i>Invalid username & password</i>

	</c:if> <br><br>
		<c:if test="${param.logout != null}">
		
		<i>You are Successfully logged out .....</i>

	</c:if>
<h2>Choose : </h2><br>

<a href="/springMvc/loginForm">Login </a><br><br>

<a href="/springMvc/registerForm">Register</a><br>


</body>
</html>
