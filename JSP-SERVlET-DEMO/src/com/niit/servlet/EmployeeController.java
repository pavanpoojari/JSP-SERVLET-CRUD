package com.niit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.niit.DAO.EmployeeDAO;
import com.niit.DAOImpl.EmployeeDAOImpl;
import com.niit.entity.Employee;

@WebServlet(urlPatterns = {"/EmployeeController.do","/EmployeeController"})
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EmployeeDAO employeeDAO = EmployeeDAOImpl.getEmployeeDAO();
	
    private static final String LIST_VIEW ="listEmployee.jsp";
    private static final String FORM_VIEW ="formEmployee.jsp";
    
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String forward = null;
		
		if(action.equals("insert")){
			forward = FORM_VIEW;
		}
		else if(action.equals("edit")){
			int id = Integer.parseInt(request.getParameter("id"));
			Employee employee = employeeDAO.get(id);
			request.setAttribute("employee", employee);
			forward = FORM_VIEW;
		}
		else if(action.equals("delete")){
			int id = Integer.parseInt(request.getParameter("id"));
			employeeDAO.delete(id);
			request.setAttribute("msg","You Have Deleted :"+id);
			request.setAttribute("employees", employeeDAO.list());
			forward = LIST_VIEW;
		}
		else{
			request.setAttribute("employees", employeeDAO.list());						
			forward = LIST_VIEW;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		double salary = Double.parseDouble(request.getParameter("salary"));
		boolean working = Boolean.parseBoolean(request.getParameter("working"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		Employee employee = new Employee();
		employee.setId(id);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setSalary(salary);
		employee.setWorking(working);

		if(id == 0){
			employeeDAO.add(employee);
		}else {
			employeeDAO.update(employee);
		}
		response.sendRedirect("index.jsp");
		//doGet(request, response);
	}
}
