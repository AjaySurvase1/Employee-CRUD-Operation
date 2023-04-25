package org.jsp.employeeApp.controller;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jsp.employeeApp.dao.EmployeeDao;
import org.jsp.employeeApp.dto.Employee;

public class EmployeeController {
	
static Scanner sc=new Scanner(System.in);
static EmployeeDao dao=new EmployeeDao();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean b=true;
		while(b) {
System.out.println("1.Register employee");
System.out.println("2.Update Employee");
System.out.println("3.Verify Employee by phone and password");
System.out.println("4.Delete Employee by phone and password");
System.out.println("5.View All Employee");

int choice=sc.nextInt();

switch(choice) {
case 1:
	save();
	break;
case 2:
	updateEmployee();
case 3:
	verify();
	break;
case 4:
	delete();
case 5:
	viewAll();
	
}

		}
	}
	public static void save() {
		String name=sc.next();
		String desg=sc.next();
		double salary=sc.nextDouble();
		long phone=sc.nextLong();
		String password=sc.next();
		
		Employee e=new Employee();
		e.setName(name);
		e.setDesg(desg);
		e.setSalary(salary);
		e.setPhone(phone);
		e.setPassword(password);
		e=dao.saveEmployee(e);
		System.out.println("Employee saved with ID: "+ e.getId());
	}
	
	public static void verify() {
		System.out.println("Enter your phone and password??");
		long phone=sc.nextLong();
		String password=sc.next();
		Employee e=dao.verifyEmployee(phone, password);
		if(e!=null) {
			System.out.println("login successful!!!");
			
			System.out.println("Id: "+e.getId());
			System.out.println("name: "+e.getName());
			System.out.println("desg: "+e.getDesg());
			System.out.println("salary: "+e.getSalary());
			System.out.println("phone: "+ e.getPhone());
		}
		else {
			System.out.println("Invalid phone number and password!");
		}
	}
	public static void updateEmployee() {
		int id=sc.nextInt();
		String name=sc.next();
		String desg=sc.next();
		double salary=sc.nextDouble();
		long phone=sc.nextLong();
		String password=sc.next();
		
		Employee e=new Employee();
		e.setId(id);
		e.setName(name);
		e.setDesg(desg);
		e.setSalary(salary);
		e.setPhone(phone);
		e.setPassword(password);
		Session s=new Configuration().configure().buildSessionFactory().openSession();
		Transaction t=s.beginTransaction();
		try {
			s.update(e);
			t.commit();
		}
		catch(Exception ex) {
			System.out.println("you have entered Invalid Id..");
		}
	}
	public static void delete() {
		int id=sc.nextInt();
//		if(id!=0) {
			System.out.println("enter phone and password..");
			long phone=sc.nextLong();
			String password=sc.next();
			boolean delete=dao.deleteEmployee(phone,password);
			if(delete)
				System.err.println("Employee Data deleted...");
//		}
//		else
//			System.err.println("enter valid Id.....");
	}
	
	public static void viewAll() {
		
		List<Employee> employees=dao.findAll();
		for(Employee E:employees)
		{
			System.out.println("Id : "+E.getId());
			System.out.println("Name : "+E.getName());
			System.out.println("Desg : "+E.getDesg());
			System.out.println("Salary : "+E.getSalary());
			System.out.println("Phone : "+E.getPhone());
			
		}
	}
}
