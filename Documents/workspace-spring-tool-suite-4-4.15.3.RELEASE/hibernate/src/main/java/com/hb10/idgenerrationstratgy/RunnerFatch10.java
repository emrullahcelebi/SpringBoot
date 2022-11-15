package com.hb10.idgenerrationstratgy;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFatch10 {

	public static void main(String[] args) {
		
		
	
		Configuration con= new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student10.class).addAnnotatedClass(Book10.class);

		SessionFactory sf= con.buildSessionFactory();//oturum grubu olusturduk
		Session session=sf.openSession();//oturum grubundan bir oturum baslattik
		
		Transaction tx = session.beginTransaction();
		
		
		
		tx.commit();
		session.close();
		sf.close();
	}
	
}
