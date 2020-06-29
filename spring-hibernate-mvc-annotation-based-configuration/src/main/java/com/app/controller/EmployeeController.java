package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.entity.Employee;
import com.app.service.EmployeeService;
import com.app.validations.EmployeeValidator;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeSerive;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewHomePage(Model model, @RequestParam(value = "id", required = false) Integer id) {
		Employee employee = new Employee();
		if (id != null) {
			employee = employeeSerive.getEmployeeById(id);
		}
		model.addAttribute("employee", employee);
		return "dashboard.tiles";
	}

	@PostMapping(value = "save")
	public String saveEmployee(Model model, @ModelAttribute("employee") @Validated Employee employee,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("employee", employee);
			return "dashboard.tiles";
		}

		boolean result = employeeSerive.saveemployee(employee);
		if (result == true) {
			redirectAttributes.addFlashAttribute("success", "Recored Saved/Updated");
		} else {
			redirectAttributes.addFlashAttribute("error", "Recored  not Saved/Updated");
		}
		return "redirect:/";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deleteEmployee(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
		boolean result = employeeSerive.deleteEmployee(id);
		if (result == true) {
			redirectAttributes.addFlashAttribute("success", "Recored Deleted");
		} else {
			redirectAttributes.addFlashAttribute("error", "Recored  not Deleted");
		}
		return "redirect:/";
	}

	@ModelAttribute("employees")
	public List<Employee> getEmployee() {
		return employeeSerive.listOfemployees();
	}

	@InitBinder("employee")
	public void formBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new EmployeeValidator());
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String aboutUs() {
		return "about.tiles";
	}
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contactUs() {
		return "contact.tiles";
	}

}
