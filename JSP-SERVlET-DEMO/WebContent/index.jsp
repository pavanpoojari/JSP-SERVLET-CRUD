<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%
	String title = "Home Page";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=title%></title>
</head>
<body>
	<jsp:forward page="EmployeeController.do?action=list"></jsp:forward>
</body>
</html>