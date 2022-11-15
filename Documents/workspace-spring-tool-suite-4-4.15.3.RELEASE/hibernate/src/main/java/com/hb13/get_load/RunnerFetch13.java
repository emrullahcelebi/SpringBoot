package com.hb13.get_load;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class RunnerFetch13 {

	public static void main(String[] args) {

		// CRUD (create,read,update,delete)
		// C-session.save
		// R-session.get,hql,sql (select)
		// U--session.update, udapte query
		// D-session.delete, hql,sql

		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student13.class);

		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();	
		
//		System.out.println("Start to execute get method");
//		Student13 student1= session.get(Student13.class, 1L);
//		System.out.println("Finish to execute get method");
//		System.out.println("Student ID: "+student1.getId());
//		System.out.println("Student Get Name: "+student1.getName());
//		
//		System.out.println("=====================================");
//		
//		System.out.println("Start to execute load method");
//		Student13 student2= session.load(Student13.class, 2L);
//		System.out.println("Finish to execute load method");
//		System.out.println("Student ID: "+student2.getId());
//		System.out.println("Student load Name: "+student2.getName());
		
		
		//db de olmayan id ler ile get load methodlarinin davranisini gorme
//		System.out.println("Start to execute get method");
//		Student13 student1= session.get(Student13.class, 5L);
//		System.out.println("Finish to execute get method");
//		
//		if(student1!=null) {
//			System.out.println("Student ID: "+student1.getId());
//			System.out.println("Student Get Name: "+student1.getName());
//		}
//		
//		
//		System.out.println("=====================================");
//		
//		System.out.println("Start to execute load method");
//		Student13 student2= session.load(Student13.class, 10L);
//		System.out.println("Finish to execute load method");
//		
//		if (student2 !=null) {
//			System.out.println("Student ID: "+student2.getId());
//			System.out.println("Student load Name: "+student2.getName());
//		}
//		
		
		
		
		//load methodu ile obje referansi alinir ve sonra delete cagirilir
		//get methodu cagirilmayarak db ye hit engellenmis olur
		Student13 studentFound= session.load(Student13.class, 1L);
		session.delete(studentFound);
		
		
		tx.commit();
		session.close();
	
		sf.close();
	}

}

