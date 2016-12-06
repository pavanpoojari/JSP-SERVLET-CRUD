package com.niit.DAO;

import java.util.List;
import com.niit.entity.Employee;

public interface EmployeeDAO {
	
	Employee get(int id);
	List<Employee> list();
	boolean add(Employee employee);
	boolean update(Employee employee);
	boolean delete(int id);
	
}
