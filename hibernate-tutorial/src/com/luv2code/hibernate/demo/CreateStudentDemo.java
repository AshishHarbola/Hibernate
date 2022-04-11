package com.luv2code.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class CreateStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create a student object
			System.out.println("creating a new student object...");
			Student tempStudent = new Student("Paul","Wall","paul@luv2code.com");
			
			//
			session.beginTransaction();
			
			session.save(tempStudent);
			
			session.getTransaction().commit();
			
			
		}
		
		finally {}
	}

}
