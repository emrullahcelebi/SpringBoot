package com.hb06.uni_onetomany;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFatch06 {

	public static void main(String[] args) {
		Configuration con= new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student06.class).addAnnotatedClass(Book06.class);

		SessionFactory sf= con.buildSessionFactory();//oturum grubu olusturduk
		Session session=sf.openSession();//oturum grubundan bir oturum baslattik
		
		Transaction tx = session.beginTransaction();
		
//	Student06 student=session.get(Student06.class, 1001);
//	System.out.println(student);

		//bir ogrenci id si ile kitaplari cekme
//		String hqlQuery1="FROM Book06 b where b.id=101";
//		Book06 book=session.createQuery(hqlQuery1,Book06.class).uniqueResult();//bir deger gelecek diye unique yapiyoruz
//		System.out.println(book);
		
//		Student06 student = session.get(Student06.class,1001);
//		student.getBookList().forEach(b->System.out.println(b));
//		
//		Book06 book= session.get(Book06.class, 101);
//		System.out.println(book);
//		
		//bir ogrencinin kitaplarini ogrenci id ye gore getirme
		String hqlQuery2="SELECT b.id, b.name FROM Student06 s INNER JOIN s.bookList b WHERE s.id=1001";
		List<Object[]> resultList1=session.createQuery(hqlQuery2).getResultList();
		resultList1.stream().forEach(oa->System.out.println(Arrays.toString(oa)));
	
		tx.commit();
		session.close();
		sf.close();
	}

}
