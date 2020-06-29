package com.app.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.entity.Employee;

public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Employee.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emp_name", "empName.required");
		
		Employee employee=(Employee)target;
		
		if(!isStringOnlyAlphabet(employee.getEmp_name()))
			errors.rejectValue("emp_name", "empName.aplha");
		
	}

	public static boolean isStringOnlyAlphabet(String str) {
		return ((str != null) && (!str.equals("")) && (str.chars().allMatch(Character::isLetter)));
	}

}
