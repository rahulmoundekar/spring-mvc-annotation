package com.app.service;

import java.util.*;

import com.app.entity.Employee;

public interface EmployeeService {

	public List<Employee> listOfemployees();

	public boolean saveemployee(Employee employee);

	public Employee getEmployeeById(Integer id);

	public boolean deleteEmployee(Integer id);

}
