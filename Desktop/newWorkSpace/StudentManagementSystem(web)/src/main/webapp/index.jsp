<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String message = request.getParameter("msg");
if(message!=null)
{
	out.print(message+"<br>");
}
%>
WELCOME TO STUDENT MANAGEMENT SYSTEM:<br>
<form action="./login" method="get">
	<label for="input-1">username :</label>
	<input id="input-1" name="uname" placeholder="Text" type="text"/><br>
	<label for="input-2">password:</label>
	<input id="input-2" name="pwd" placeholder="Text" type="password"/><br>
	<input type="submit" value="Submit" id="button-1"/><br>
	new user? <a href="register.html">click here</a>	
</form>

</body>
</html>