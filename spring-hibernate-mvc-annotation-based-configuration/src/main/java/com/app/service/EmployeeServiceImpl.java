package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Employee;
import com.app.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public List<Employee> listOfemployees() {
		return employeeRepo.listOfemployees();
	}

	@Override
	public boolean saveemployee(Employee employee) {
		return employeeRepo.saveemployee(employee);
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return employeeRepo.getEmployeeById(id);
	}

	@Override
	public boolean deleteEmployee(Integer id) {
		return employeeRepo.deleteEmployee(id);
	}

}
