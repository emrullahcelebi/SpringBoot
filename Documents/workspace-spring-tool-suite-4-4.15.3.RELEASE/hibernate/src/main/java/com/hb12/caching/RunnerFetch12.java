package com.hb12.caching;

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

public class RunnerFetch12 {

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
		
		
//		System.out.println("First get for id 1");
//		Student12 student1= session.get(Student12.class, 1L);
		
//		session.clear();
//		
//		System.out.println("Second get for id 1");
//		Student12 student2= session.get(Student12.class, 1L);
		
		
//		System.out.println("Second get for id 2");
//		Student12 student2= session.get(Student12.class, 2L);
//		
		
		String hqlQuery1="FROM Student12 s where s.id=1";
		Query<Student13> query1=session.createQuery(hqlQuery1,Student13.class);
		query1.setCacheable(true);
		
		Student13 student= query1.uniqueResult();
		System.out.println(student);
		
		System.out.println("=================================");
		
		String hqlQuery2="FROM Student12 s where s.id=1";
		Query<Student13> query2=session.createQuery(hqlQuery2,Student13.class);
		query2.setCacheable(true);
		
		Student13 student2= query2.uniqueResult();
		System.out.println(student2);
		
		
		
		
		tx.commit();
		session.close();
		
		//second level cache i gostermek icin koyduk
//		Session session2= sf.openSession();
//		Transaction tx2=session2.beginTransaction();
//		System.out.println("First get for id 1");
//		Student12 student2= session2.get(Student12.class, 2L);
//		session2.close();
		sf.close();
	}

}

