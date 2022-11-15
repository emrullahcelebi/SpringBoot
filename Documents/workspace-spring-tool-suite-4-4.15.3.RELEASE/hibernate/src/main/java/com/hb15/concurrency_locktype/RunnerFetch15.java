package com.hb15.concurrency_locktype;

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

public class RunnerFetch15 {

	public static void main(String[] args) {

		// CRUD (create,read,update,delete)
		// C-session.save
		// R-session.get,hql,sql (select)
		// U--session.update, udapte query
		// D-session.delete, hql,sql

		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student15.class);

		SessionFactory sf = con.buildSessionFactory();
//		Session session1 = sf.openSession();
//
//		Transaction tx1 = session1.beginTransaction();	
//	
//		Session session2=sf.openSession();
//		Transaction tx2=session2.beginTransaction();
//		
//		Student15 student1=session1.get(Student15.class, 1L);
//		
//		Student15 student2= session2.get(Student15.class, 1L);
//		
//		//OptimisticLockException
//		student1.setName("session1 student");
//		student2.setName("session2 student");
//		
//		
//		
//		tx2.commit();
//		tx1.commit();
//		
//		session2.close();
//		session1.close();

		Session session1 = sf.openSession();
		Transaction tx1 = session1.beginTransaction();	
		
		tx1.commit();
		session1.close();
		sf.close();
	}

}

