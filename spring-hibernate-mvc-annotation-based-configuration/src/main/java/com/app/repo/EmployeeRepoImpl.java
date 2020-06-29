package com.app.repo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Employee;

@Repository
@Transactional
public class EmployeeRepoImpl implements EmployeeRepo {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listOfemployees() {
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Employee.class);
		return cr.list();
	}

	@Override
	public boolean saveemployee(Employee employee) {
		Session session = sessionFactory.openSession();
		if (employee.getId() != null) {
			Employee employee1 = (Employee) session.get(Employee.class, employee.getId());
			employee1.setEmp_name(employee.getEmp_name());
			session.update(employee1);
			session.beginTransaction().commit();
			return true;
		} else {
			session.save(employee);
			return true;
		}
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return (Employee) sessionFactory.openSession().get(Employee.class, id);
	}

	@Override
	public boolean deleteEmployee(Integer id) {
		Session session = sessionFactory.openSession();
		Employee employee = (Employee) session.get(Employee.class, id);
		session.delete(employee);
		session.beginTransaction().commit();
		return true;
	}

}
