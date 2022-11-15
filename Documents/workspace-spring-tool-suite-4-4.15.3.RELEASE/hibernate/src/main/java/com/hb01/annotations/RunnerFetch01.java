package com.hb01.annotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch01 {

	public static void main(String[] args) {
		Configuration con = new     
                Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student01.class);

		SessionFactory sf = con.buildSessionFactory();

		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
			
		Student01 student1= session.get(Student01.class, 1001);
		Student01 student2= session.get(Student01.class, 1002);
		Student01 student3= session.get(Student01.class, 1003);
//		System.out.println(student1);
//		System.out.println(student2);
//		System.out.println(student3);
		
		
		
		//SQL ornegi
//		String sqlQuery1="SELECT * FROM t_student01";
//		List<Object[]> resultList1= session.createSQLQuery(sqlQuery1).getResultList();
//		
//		Query query1= session.createQuery(sqlQuery1);
//		List<Object[]>resultList2=query1.getResultList();
//		
//		for(Object[] object:resultList2) {
//			System.out.println(Arrays.toString(object));
//		}
		
		//HQL ornegi--SQL sorgusunda FROM dan sonra sinif ismi kullanilmali yoksa is not mapped alinir
//		String hqlQuery1="FROM Student01";
//		List<Student01> resultList3 =session.createQuery(hqlQuery1,Student01.class).getResultList();
//		
//		for(Student01 student01:resultList3) {
//			System.out.println(student01);
//		}
		
		//Donecek kaydin unique bir kayit olduguna eminseniz uniqueResult() methodu kullanilabilir
		
		String sqlQuery2="SELECT * FROM t_student01 where student_name='John Coffee'";
		Object[] uniqueResult1 = (Object[]) session.createSQLQuery(sqlQuery2).uniqueResult();
		
		
		
		//HQL ile uniqeuer result sorgulama
		//String hqlQuery2="FROM Student01 where name='John Coffee'";
		//System.out.println(Arrays.toString(uniqueResult1));
		//System.out.println(uniqueResult1[0]+":"+uniqueResult1[1]+":"+uniqueResult1[2]);
		
		
		
		
		//HQL ile Alias kullanma ornegi
//		String hqlQuery2="FROM Student01 std where name='John Coffee'";
//		Student01 uniqueResult2= session.createQuery(hqlQuery2,Student01.class).uniqueResult();
//		System.out.println(uniqueResult2);
		
		//HQL ile belirli degiskenleri alma
		//grade degeri 10 olan ogrencileri getiriniz
//		String hqlQuery3="SELECT  s.id,s.name FROM Student01 s where s.grade=10" ;
//		List<Object[]> resultList4= session.createQuery(hqlQuery3).getResultList();
//		
//		for (Object[] objects : resultList4) {
//			System.out.println(Arrays.toString(objects));
//		}
		
		
		String hqlQuery4="FROM Student01 s order by s.id desc";
		List<Student01> resultList5=session.createQuery(hqlQuery4,Student01.class).getResultList();
		
		for (Student01 student01 : resultList5) {
			System.out.println(student01);
		}
		
		tx.commit();

		session.close();

		sf.close();

	}

}
