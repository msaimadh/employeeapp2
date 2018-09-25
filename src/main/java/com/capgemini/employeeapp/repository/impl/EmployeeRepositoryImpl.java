package com.capgemini.employeeapp.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

import com.capgemini.employeeapp.entities.Employee;
import com.capgemini.employeeapp.repository.EmployeeRepository;
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
@Override
	public Employee addEmployee(Employee employee) {
	jdbcTemplate.update("INSERT INTO employees values(?,?,?,?)",new Object[] {employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeDepartment(),employee.getEmployeeSalary()});
	return employee;
		
	}
@Override
	public Employee updateEmployee(Employee employee) {
		int count=jdbcTemplate.update("UPDATE employees SET employee_salary=?,employee_department=?,employee_name=? WHERE employee_id=?",new Object[] {employee.getEmployeeSalary(),employee.getEmployeeDepartment(),employee.getEmployeeName(), employee.getEmployeeId()});
		return (count != 0) ? employee : findEmployeebyId(employee.getEmployeeId());
		
		
	}
@Override
	public boolean deleteEmployee(int employeeId) {
		int count=jdbcTemplate.update("DELETE FROM employees WHERE employee_id = ?",new Object[] {employeeId});
		return (count!=0);
		
		
	}
@Override
	public Employee findEmployeebyId(int employeeId) {
		return jdbcTemplate.queryForObject("SELECT*FROM employees  WHERE employee_id=?", new Object[] {employeeId}, new EmployeeRowMapper());
		
	}
@Override
	public List<Employee> findAllEmployees() {
		
		return jdbcTemplate.query("SELECT * FROM employees", new Object[] {}, new EmployeeRowMapper());
		
	}


private class EmployeeRowMapper implements RowMapper<Employee>{
@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setEmployeeId(rs.getInt(1));
		employee.setEmployeeName(rs.getString(2));
		employee.setEmployeeSalary(rs.getDouble(4));
		employee.setEmployeeDepartment(rs.getString(3));
		return employee;
}
}
}
