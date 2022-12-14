package com.hb05.manytoone;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFatch05 {

	public static void main(String[] args) {
		Configuration con= new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student05.class).addAnnotatedClass(University.class);

		SessionFactory sf= con.buildSessionFactory();//oturum grubu olusturduk
		Session session=sf.openSession();//oturum grubundan bir oturum baslattik
		
		Transaction tx = session.beginTransaction();
		
		Student05 student= session.get(Student05.class, 1001);
		//System.out.println(student.getUniversity());
	
		String hqlQuery1="FROM Student05 s WHERE s.university.id=1";
		List<Student05> resultList1= session.createQuery(hqlQuery1,Student05.class).getResultList();
		resultList1.forEach(s->System.out.println(s));
		
		tx.commit();
		session.close();
		sf.close();

	}

}
