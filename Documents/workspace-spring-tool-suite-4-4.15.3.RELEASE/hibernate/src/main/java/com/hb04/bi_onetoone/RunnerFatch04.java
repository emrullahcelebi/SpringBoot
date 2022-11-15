package com.hb04.bi_onetoone;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFatch04 {

	public static void main(String[] args) {
		
		
		Configuration con= new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student04.class).addAnnotatedClass(Diary02.class);

		SessionFactory sf= con.buildSessionFactory();
		Session session=sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		//Student i fetch ediyoruz id ile
//		Student04 student= session.get(Student04.class, 1001);
//		
//		//Diary fetch ediyoruz
//		Diary02 diary= session.get(Diary02.class, 101);
//		
//		System.out.println(student);
//		System.out.println("----------------------");
//		System.out.println(student.getDiary());
//		System.out.println("----------------------");
//		System.out.println(diary);
//		System.out.println("----------------------");
//		System.out.println(diary.getStudent());
//		System.out.println("----------------------");

		/*
		 * Hibernate: 
    select
        student04x0_.std_name as col_0_0_,
        diary02x1_.name as col_1_0_ 
    from
        Student04 student04x0_ 
    inner join
        Diary02 diary02x1_ 
            on (
                student04x0_.id=diary02x1_.std_id
                
                
            )
		 * 
		 * */
		
		
		
		String hqlQuery1="SELECT s.name,d.name FROM Student04 s INNER JOIN FETCH Diary02 d on s.id=d.student";
		List<Object[]>resultList1= session.createQuery(hqlQuery1).getResultList();
		
		for (Object[] objects : resultList1) {
			System.out.println(Arrays.toString(objects));
		}
		
		resultList1.forEach(oa->{System.out.println(Arrays.toString(oa));});
		
		
		
		tx.commit();
		session.close();
		sf.close();
		
		
		
	}

}
