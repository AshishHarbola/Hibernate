package com.luv2code.hibernate.demo;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;



public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			
			//create a course
			
			Course tempCourse = new Course("Pacman - How to score One Million Points");
			
			//add some reviews
			
			/*
			 * tempCourse.addReview(new Review("Great Course...loved it!!"));
			 * tempCourse.addReview(new Review("Cool Course, Good job!!"));
			 * tempCourse.addReview(new Review("What a dumb course!!"));
			 */
			
			//add some review : without using convenience method
			List<Review> reviewList= new ArrayList<>();
			
			reviewList.add(new Review("Great Course...loved it!!"));
			reviewList.add(new Review("Cool Course, Good job!!"));
			reviewList.add(new Review("What a dumb course!!"));
			
			tempCourse.setReviews(reviewList);
			
			
			//save the course...and leverage the cascade all
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			
			session.save(tempCourse);
			
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
