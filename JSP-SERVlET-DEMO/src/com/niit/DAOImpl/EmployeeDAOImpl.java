package com.niit.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.niit.DAO.EmployeeDAO;
import com.niit.entity.Employee;
import com.niit.util.DBUtil;
//import com.sun.org.apache.regexp.internal.recompile;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static EmployeeDAO employeeDAO = null;
	Connection con = null;

	private EmployeeDAOImpl() {
		con = DBUtil.getConnection();
	}

	public static EmployeeDAO getEmployeeDAO() {
		if (employeeDAO == null) {
			employeeDAO = new EmployeeDAOImpl();
		}
		return employeeDAO;
	}

	@Override
	public Employee get(int id) {
		Employee employee = null;
		String selectSQL = "SELECT * FROM employee WHERE id = ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(selectSQL)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setWorking(rs.getBoolean("working"));
			}
		
		}catch(SQLException ex){
			System.out.println("SQL Error: " + ex.getMessage());
		}
		return employee;
	}

	@Override
	public List<Employee> list() {
		List<Employee> employees = new ArrayList<>();
		String selectSQL = "SELECT * FROM employee";
		Employee employee = null;

		try (Statement stmt = con.createStatement()) {

			ResultSet rs = stmt.executeQuery(selectSQL);

			while (rs.next()) {
				employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setWorking(rs.getBoolean("working"));
				employees.add(employee);
			}

		} catch (SQLException ex) {
			System.out.println("SQL Error: " + ex.getMessage());
		}
		return employees;
	}

	@Override
	public boolean add(Employee employee) {
		
		String insertSQL = "INSERT INTO employee "
				+ "(first_name, last_name, salary, working) VALUES"
				+ "(?,?,?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(insertSQL)) {

			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getLastName());
			pstmt.setDouble(3, employee.getSalary());
			pstmt.setBoolean(4, employee.isWorking());

			pstmt.execute();
			return true;

		} catch (SQLException ex) {
			System.out.println("SQL Error: " + ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Employee employee) {
		String updateSQL  = "UPDATE employee SET "
				+ "first_name=?,"
				+ "last_name=?,"
				+ "salary=?,"
				+ "working=?"
				+ " WHERE id =?";
		try (PreparedStatement pstmt = con.prepareStatement(updateSQL)) {
			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getLastName());
			pstmt.setDouble(3, employee.getSalary());
			pstmt.setBoolean(4, employee.isWorking());
			pstmt.setInt(5, employee.getId());
			pstmt.execute();
			return true;
			
		}catch (SQLException ex) {
			System.out.println("SQL Error: " + ex.getMessage());
			return false;
		}
	}
	@Override
	public boolean delete(int id) {
		String deleteSQL = "DELETE FROM employee WHERE id=?";
		try (PreparedStatement pstmt = con.prepareStatement(deleteSQL)) {
			
			pstmt.setInt(1,id);
			pstmt.execute();
			return true;
			
		}catch(SQLException ex){
			System.out.println("SQL Error: " + ex.getMessage());
			return false;
		}
	}
}
