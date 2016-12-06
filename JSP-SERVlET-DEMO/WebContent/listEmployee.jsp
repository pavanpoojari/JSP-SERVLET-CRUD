<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List"%>
<%@ page import="com.niit.entity.Employee"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title># LIST #</title>
</head>
<body>
	<%
		String msg = (String)request.getAttribute("msg");
		List<Employee> employees = (List<Employee>) request.getAttribute("employees");
		if(msg != null){
			out.println(""+msg);
		}
	%>
	<table border="1" width="80%" align="center">
		<caption>Employee Details</caption>
		<thead>
			<tr align="center">
				<th>First Name</th>
				<th>Last Name</th>
				<th>Salary</th>
				<th>Working</th>
				<th>&#160;</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Employee employee : employees) {
			%>
			<tr align="center">
				<td><%=employee.getFirstName()%></td>
				<td><%=employee.getLastName()%></td>
				<td><%=employee.getSalary()%></td>
				<td><%=employee.isWorking()%></td>
				<td>
					<a href="EmployeeController.do?action=edit&id=<%=employee.getId()%>">EDIT</a>
					<a href="EmployeeController.do?action=delete&id=<%=employee.getId()%>">DELETE</a>
				</td>
			</tr>
			<%
				}
			%>
		</tbody>
		<tfoot>
			<tr>
				<td align="center" colspan="5">
				<a href="EmployeeController.do?action=insert">ADD EMPLOYEE</a>
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>