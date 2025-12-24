<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> Welcome to Registeration ....</h1>

<form:form action="/process-register" method="post" modelAttribute="userDto">
  
 Username :  <form:input  path="username"/><br><br>
 Password :  <form:input    path="password" /><br><br>
 
 <input type="submit" value="Sign Up"/> 

</form:form>


</body>
</html>