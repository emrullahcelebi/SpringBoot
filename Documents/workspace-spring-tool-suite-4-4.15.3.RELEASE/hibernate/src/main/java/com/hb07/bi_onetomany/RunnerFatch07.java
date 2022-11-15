package com.hb07.bi_onetomany;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFatch07 {

	public static void main(String[] args) {
		Configuration con= new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student07.class).addAnnotatedClass(Book07.class);

		SessionFactory sf= con.buildSessionFactory();//oturum grubu olusturduk
		Session session=sf.openSession();//oturum grubundan bir oturum baslattik
		
		Transaction tx = session.beginTransaction();
		
		
		//Student07 student=session.get(Student07.class, 1001);

		//student.getBookList().forEach(System.out::println);
		//student.getBookList().forEach(b->System.out.println(b));
		
		//ogrencinin bilgilerini getirelim
		
		//SQLQUERY
//		String sqlQuery1="SELECT s.student_name, b.name FROM student07 s INNER JOIN book07 b ON s.id=b.student_id ";
//		List<Object[]>resultList1=session.createSQLQuery(sqlQuery1).getResultList();
//		
//		for (Object[] objects : resultList1) {
//			System.out.println(Arrays.toString(objects));
//		}
		
		//HQLQUERY
//		String hqlQuery1="SELECT s.name, b.name FROM Student07 s INNER JOIN FETCH Book07 b ON s.id=b.student";
//		List<Object[]> resultQuery2=session.createQuery(hqlQuery1).getResultList();
//		
//		resultQuery2.forEach(oa->System.out.println(Arrays.toString(oa)));

		
		
//		String sqlQuery1="DELETE FROM book07";
//		int numOfRec1= session.createSQLQuery(sqlQuery2).executeUpdate();
//		System.out.println("Effected Row count: "+numOfRec1);
//		
//		String sqlQuery2="DELETE FROM student07";
//		int numOfRec2= session.createSQLQuery(sqlQuery2).executeUpdate();
//		System.out.println("Effected Row count: "+numOfRec2);
		
		
//		String hqlQuery1="DELETE FROM Book07";
//		int numOfRec3= session.createSQLQuery(hqlQuery1).executeUpdate();
//		System.out.println("Effected Row count: "+numOfRec3);
//		
//		String hqlQuery2="DELETE FROM Student07";
//		int numOfRec4= session.createSQLQuery(hqlQuery2).executeUpdate();
//		System.out.println("Effected Row count: "+numOfRec4);
		
		//kitap ismine gore kitap silmeyi saglayan hql 
		
//		String hqlQuery3="DELETE FROM Book07 b WHERE b.name='John''s book'";
//		int numOfRec5 = session.createQuery(hqlQuery3).executeUpdate();
//		System.out.println("Effected Row count : " + numOfRec5);
		
//		String hqlQuery4="DELETE FROM Book07 b WHERE b.student=1001";
//		int numOfRec6=session.createQuery(hqlQuery4).executeUpdate();
//		System.out.println("Effected Row count : "+numOfRec6);
		
//		String hqlQuery5="DELETE FROM Student07 s WHERE s.id=1001";
//		int numOfRec7=session.createQuery(hqlQuery5).executeUpdate();
//		System.out.println("Effected Row count : "+numOfRec7);
		
		/*
		 * student07" violates foreign key constraint "fk95pa12xmsmpq144c2dq69x5m1" on table "book07"
		 * 
		 * parent chil iliskisinden oturu once child silmek lazim
		 * */
//		String hqlQuery6="DELETE FROM Student07 s WHERE s.id=1003";
//		int numOfRec8=session.createQuery(hqlQuery6).executeUpdate();
//		System.out.println("Effected Row count : "+numOfRec8);
//		
		
		//hibernate silme yontemi
		//Student07 student=session.get(Student07.class, 1001);
		//session.delete(student);
		
		//student.getBookList().set(0, null);//bu sekilde de silme yapabiliriz
		
//		String hqlQuery7="SELECT s FROM Student07 s JOIN Book07 b ON s.id=b.student";
//		session.createQuery(hqlQuery7).getResultList();
		
//		String hqlQuery7="SELECT s FROM Student07 s JOIN s.bookList";
//		session.createQuery(hqlQuery7).getResultList();
//		
		String hqlQuery8="SELECT s FROM Student07 s JOIN s.bookList b WHERE b.name LIKE '%Book%'";
		List<Student07>resultList1=session.createQuery(hqlQuery8,Student07.class).getResultList();
		
		for (Student07 student07 : resultList1) {
			System.out.println(student07);
		}
		
		
		tx.commit();
		session.close();
		sf.close();
	}

}
