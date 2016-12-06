<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.niit.entity.Employee"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title># FORM #</title>
</head>
<body>
	<%
		Employee employee = (Employee) request.getAttribute("employee");
		if (employee == null) {
			employee = new Employee();
			employee.setFirstName("");
			employee.setLastName("");
		}
	%>
	<form action="EmployeeController.do" method="post">
		<table border="1" width="80%" align="center">
			<caption>Employee Details</caption>
			<tr>
				<td><label for="firstName">Enter First Name: </label></td>
				<td>
					<input type="text" name="firstName" value="<%=employee.getFirstName()%>" /> 
					<input type="hidden" name="id" value="<%=employee.getId()%>" />
				</td>
			</tr>
			<tr>
				<td><label for="lastName">Enter Last Name: </label></td>
				<td><input type="text" name="lastName"
					value="<%=employee.getLastName()%>" /></td>
			</tr>
			<tr>
				<td><label for="salary">Enter Salary: </label></td>
				<td><input type="text" name="salary"
					value="<%=employee.getSalary()%>" /></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="checkbox" name="working" value="true" checked="<%if (employee.isWorking()) out.print("checked");%>" /> 
					<label for="working"> &nbsp;: Is Presently Working</label>
				</td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="ADD EMPLOYEE" /></td>
			</tr>
		</table>
	</form>
</body>
</html>