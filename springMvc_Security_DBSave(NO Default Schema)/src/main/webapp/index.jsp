<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<body>
	<h1>Welcome To Spring Security MVC ......</h1>
	<br>

<!-- show this link for user who has role = user -->
	<sec:authorize access='hasAuthority("ROLE_USER")'>

		<a href="/springMvc/user">User DashBoard </a>
		<br>
		<br>

	</sec:authorize>

<!-- show this link for user who has role = admin -->

	<sec:authorize access='hasAuthority("ROLE_ADMIN")'>
		
		<a href="/springMvc/admin">Admin DashBoard</a>
		<br>
		<br>
	</sec:authorize>


</body>
</html>
