 package org.jsp.employeeApp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jsp.employeeApp.dto.Employee;

public class EmployeeDao {

	public Employee saveEmployee(Employee e) {
		Session s=new Configuration().configure().buildSessionFactory().openSession();
		Transaction t=s.beginTransaction();
		s.save(e);
		t.commit();
		return e;
	}
	
	public Employee updateEmployee(Employee e) {
		Session s=new Configuration().configure().buildSessionFactory().openSession();
		Transaction t=s.beginTransaction();
		s.update(e);
		t.commit();
		return e;
	}
	public Employee verifyEmployee(long phone ,String password) {
		String qry="select e from Employee e where phone=?1 and password=?2";
		Session s=new Configuration().configure().buildSessionFactory().openSession();
		Query<Employee> q=s.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		List<Employee> emp=q.getResultList();
		if(emp.size()>0) 
			return emp.get(0);
		return null;
			
	}
	
	public boolean deleteEmployee(long phone,String password) {
		Session s=new Configuration().configure().buildSessionFactory().openSession();
		Employee e=verifyEmployee(phone , password);
		if(e!=null) {
			s.delete(e);
			Transaction t=s.beginTransaction();
			t.commit();
			return true;
		}
		return false;
	}
	
	public  List<Employee> findAll(){
		Session s=new Configuration().configure().buildSessionFactory().openSession();
		Query<Employee> q=s.createQuery("select e from Employee e"); 
		return q.getResultList();
	}
}
