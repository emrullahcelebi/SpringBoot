package com.hb08.manytomany;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFatch08 {

	public static void main(String[] args) {
		Configuration con= new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student08.class).addAnnotatedClass(Book08.class);

		SessionFactory sf= con.buildSessionFactory();//oturum grubu olusturduk
		Session session=sf.openSession();//oturum grubundan bir oturum baslattik
		
		Transaction tx = session.beginTransaction();
		
//		Student08 student=session.get(Student08.class, 1001);
//		System.out.println(student.getBookList());
//		
//		Book08 book=session.get(Book08.class, 101);
//		System.out.println(book.getStudents());
	
		
		//istege gore alanlar seciyoruz
		//burada FATCH kullanmiyoruz
		String hqlQuery1="SELECT s.name, b.name FROM Student08 s JOIN s.bookList b";
		List<Object[]> resultList= session.createQuery(hqlQuery1).getResultList();
		
		for (Object[] objects : resultList) {
			System.out.println(Arrays.toString(objects));
		}
		
		
		tx.commit();
		session.close();
		sf.close();
		
		
	}

}
