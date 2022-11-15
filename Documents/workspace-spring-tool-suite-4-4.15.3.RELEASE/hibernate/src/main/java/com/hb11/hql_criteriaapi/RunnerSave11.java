package com.hb11.hql_criteriaapi;


import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave11 {

	public static void main(String[] args) {
		
		
		Configuration con= new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student11.class);

		SessionFactory sf= con.buildSessionFactory();//oturum grubu olusturduk
		Session session=sf.openSession();//oturum grubundan bir oturum baslattik
		
		Transaction tx = session.beginTransaction();
		
		Random random=new Random();
		
		for (int i = 1; i <= 50; i++) {
			Student11 student =new Student11();
			student.setName("Student Name "+i);
			student.setmathGrade(random.nextInt(100));
			session.save(student);
			
		}
		
		tx.commit();
		session.close();
		sf.close();
	}

}
