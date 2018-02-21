package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class EmployeeDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//CREATE an employee
			System.out.println("Creating a new employee...");
			Employee tempEmployee = new Employee("John", "Smith", "Rackspace");
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//session.save(tempEmployee);
			
			//commit the transaction
			session.getTransaction().commit();
			
			//READ an employee by id
			int id = 1;
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("\n\nReading from employee table by id...");
			
			List<Employee> employees = session.createQuery("from Employee e where e.id=" + id).getResultList();
			
			for(Employee listEmployee : employees) {
				System.out.println(listEmployee);
			}
			
			session.getTransaction().commit();
			
			//READ an employee by company
			String company = "Rackspace";
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("\n\nReading from employee table by company...");
			
			List<Employee> companyEmployees = session.createQuery("from Employee e where e.company='Rackspace'").getResultList();
			
			for(Employee listEmployee : companyEmployees) {
				System.out.println(listEmployee);
			}
			
			session.getTransaction().commit();
			
			//DELETE an employee by primary key
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("\n\nDeleting employee...");
			session.createQuery("delete from Employee where id=3").executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
