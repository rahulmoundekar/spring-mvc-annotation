package com.app.repo;

import java.util.List;

import com.app.entity.Employee;

public interface EmployeeRepo {
	public List<Employee> listOfemployees();

	public boolean saveemployee(Employee employee);

	public Employee getEmployeeById(Integer id);

	public boolean deleteEmployee(Integer id);
}
