<%@ page language="java" 
import="com.src.service.*,com.src.model.*" 
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
StudentServiceInterface ssi = new StudentServiceImpl();
int id = Integer.parseInt(request.getParameter("id"));
Student s = ssi.display(id);
%>
<form action="./updatestudent" method="post">
	<label for="input-1">enter student id:</label>
	<input id="input-1" value="<%=s.getId() %>" placeholder="enter student id" name="sid" type="text"/><br/>
	<label for="input-1">enter student name:</label>
	<input id="input-1" value="<%=s.getName() %>" placeholder="enter student name" name="sname" type="text"/><br/>
	<label for="input-1">enter student mobile no:</label>
	<input id="input-1" value="<%=s.getMobileno() %>" placeholder="enter student mobile no" name="smob" type="text"/><br/>
	<label for="input-1">enter student emailid:</label>
	<input id="input-1" value="<%=s.getEmailid()%>" placeholder="enter student emailid" name="semailid" type="text"/><br/>
	<label for="input-1">enter student m1 marks:</label>
	<input id="input-1" value="<%=s.getMarks1() %>" placeholder="enter m1 marks" name="sm1" type="text"/><br/>
	<label for="input-1">enter student m2 marks:</label>
	<input id="input-1" value="<%=s.getMarks2() %>" placeholder="enter m2 marks" name="sm2" type="text"/><br/>
	<label for="input-1">enter student m3 marks:</label>
	<input id="input-1" value="<%=s.getMarks3() %>" placeholder="enter m3 marks" name="sm3" type="text"/><br/>
	<input type="submit" value="Submit" id="button-1"/>	
</form>
</body>
</html>