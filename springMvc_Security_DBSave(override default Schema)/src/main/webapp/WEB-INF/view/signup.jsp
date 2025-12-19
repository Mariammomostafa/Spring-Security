<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Sign Up Form</h1>


<!-- ${pageContext.request.contextPath } = /spring_mvc_security -->


<form:form action="/springMvc_NoXml/process-register" method="post" modelAttribute="signupDto">
  
 Username :  <form:input  path="username"/><br><br>
 Password :  <form:input    path="password" /><br><br>
 Email :  <form:input    path="email" /><br><br>
 
 <input type="submit" value="Sign Up"/> 

</form:form>


</body>
</html>