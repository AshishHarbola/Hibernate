package com.luv2code.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;



public class CreateInstructorDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			
			Instructor tempInstructor =
					new Instructor("Ashish","Harbola","ashish.harbola@luv2code.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://www.luv2code.com/youtube",
							"Running!!!");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start a transaction
			session.beginTransaction();
			
			//Note: this will also save the details object
			//because of CascadeType.All
			
			System.out.println("saving instructor: "+tempInstructor);
			session.save(tempInstructor);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
		}
		
		finally {
			//add clean up code
			
			session.close();
			
			factory.close();
			
		}
	}

}
