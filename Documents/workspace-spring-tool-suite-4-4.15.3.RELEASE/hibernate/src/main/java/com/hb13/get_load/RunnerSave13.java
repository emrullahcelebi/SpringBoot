package com.hb13.get_load;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class RunnerSave13 {

	public static void main(String[] args) {
		
		Student13 student1=new Student13();
		student1.setName("John Coffee");
		student1.setmathGrade(10);
		
		Student13 student2=new Student13();
		student2.setName("Jemes Bond");
		student2.setmathGrade(11);
		
		Student13 student3=new Student13();
		student3.setName("Tony Stark");
		student3.setmathGrade(9);
		
		Configuration con= new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student13.class);

		SessionFactory sf= con.buildSessionFactory();//oturum grubu olusturduk
		Session session=sf.openSession();//oturum grubundan bir oturum baslattik
		
		Transaction tx = session.beginTransaction();
			
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
	
		
		tx.commit();
		session.close();
		sf.close();
	}

}
