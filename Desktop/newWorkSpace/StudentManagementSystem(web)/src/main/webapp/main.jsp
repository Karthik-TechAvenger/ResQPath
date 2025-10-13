<%@ page language="java" 
	import="com.src.service.*,java.util.ArrayList,com.src.model.*" 
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
ArrayList<Student> stds=ssi.display();
%>
<%
String message = request.getParameter("msg");
if(message!=null)
{
out.print(message);	
}
%>
<table border="2" bgcolor=pink>
	<tr bgcolor="yellow">
		<th>student id</th>
		<th>student name</th>
		<th>student mobile no</th>
		<th>student emailid</th>
		<th>student m1 marks</th>
		<th>student m2 marks</th>
		<th>student m3 marks</th>
		<th>student total marks</th>
		<th>student percentage</th>
		<th>student grade</th>
		<th colspan="2"><a href="addstudent.jsp">Add student</a></th>
	</tr>
<%
for(Student s:stds)
{
	
%>
	<tr>
		<td><%=s.getId() %></td>
		<td><%=s.getName() %></td>
		<td><%=s.getMobileno() %></td>
		<td><%=s.getEmailid() %></td>
		<td><%=s.getMarks1() %></td>
		<td><%=s.getMarks2() %></td>
		<td><%=s.getMarks3() %></td>
		<td><%=s.getTotalmarkssecured()%></td>
		<td><%=s.getPercentage() %></td>
		<td><%=s.getGrade() %></td>
		<td><a href="./delete?id=<%=s.getId() %>">del</a></td>
		<td><a href="updatestudent.jsp?id=<%=s.getId() %>">update</a></td>
	</tr>
	<%	
}
%>
</table>
</body>
</html>