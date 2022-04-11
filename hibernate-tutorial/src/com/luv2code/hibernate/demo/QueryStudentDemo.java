package com.luv2code.hibernate.demo;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//query student: lastName = "doe"
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			//display the students
			System.out.println("\n\nStudents whose last name of doe"); 
			displayStudents(theStudents);
			
			//query students: lastName='Doe' OR firstName='Daffy'
			theStudents =
					session.createQuery("from Student s where"
							+" s.lastName='Doe' OR s.firstName='Daffy'").list();
			
			//display the students
			System.out.println("\n\nStudents whose last name of 'Doe' OR firstName='Daffy'"); 
			displayStudents(theStudents);
			
			
			//query student where email LIKE %luv2code.com'
			theStudents = session.createQuery("from Student s where"
					+ " s.email LIKE '%luv2code.com'").list(); 
			
			//display the students
			System.out.println("\n\nStudents whose where email LIKE %luv2code.com"); 
			displayStudents(theStudents);
			
			//query student where email LIKE %gamil.com'
			theStudents = session.createQuery("from Student s where"
					+ " s.email LIKE '%gmail.com'").list(); 
			
			//display the students
			System.out.println("\n\nStudents whose where email LIKE %gmail.com"); 
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			
		}
		
		finally {}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
