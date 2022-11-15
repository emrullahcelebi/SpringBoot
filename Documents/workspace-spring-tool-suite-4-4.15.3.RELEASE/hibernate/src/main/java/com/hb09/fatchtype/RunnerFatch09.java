package com.hb09.fatchtype;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFatch09 {

	public static void main(String[] args) {
		
		/*
		 * Student09 std1=getStudent(1001);
		List<Book09> bookList = std1.getBookList();
		
		for (Book09 book09 : bookList) {
			System.out.println(book09);
			
		}
		 * 
		 */
		
	
		Configuration con= new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student09.class).addAnnotatedClass(Book09.class);

		SessionFactory sf= con.buildSessionFactory();//oturum grubu olusturduk
		Session session=sf.openSession();//oturum grubundan bir oturum baslattik
		
		Transaction tx = session.beginTransaction();
		
		//Student09 student= session.get(Student09.class, 1002);
		
		//Book09 book= session.get(Book09.class, 101);
		
		//Student09 student= session.get(Student09.class, 1001);
		
//		for (Book09 book : student.getBookList()) {
//			System.out.println(book);
//		}
		
		//List<Book09> bookList= student.getBookList();
		
		//--JOIN VS JOIN FETCH
		
		//JOIN query
		/*
		 *  select
        student09x0_.id as id1_1_,
        student09x0_.grade as grade2_1_,
        student09x0_.student_name as student_3_1_ 
    from
        Student09 student09x0_ 
    inner join
        Book09 booklist1_ 
            on student09x0_.id=booklist1_.student_id
		 */
		String hqlQuery1= "SELECT s FROM Student09 s JOIN s.bookList";
		session.createQuery(hqlQuery1).getResultList();
		
		
		//JOIN FETCH
		/*
		 * select
        student09x0_.id as id1_1_0_,
        booklist1_.id as id1_0_1_,
        student09x0_.grade as grade2_1_0_,
        student09x0_.student_name as student_3_1_0_,
        booklist1_.name as name2_0_1_,
        booklist1_.student_id as student_3_0_1_,
        booklist1_.student_id as student_3_0_0__,
        booklist1_.id as id1_0_0__ 
    from
        Student09 student09x0_ 
    inner join
        Book09 booklist1_ 
            on student09x0_.id=booklist1_.student_id
		 */
//		String hqlQuery1= "SELECT s FROM Student09 s JOIN FETCH s.bookList";
//		session.createQuery(hqlQuery1).getResultList();
		
		tx.commit();
		session.close();
		
		/*
		 * FETCH TYPE LAZY ise asagidaki code session close olduktan sonra LazyInitializationException throw eder
		 * for (Book09 book : student.getBookList()) {
			System.out.println(book);
		}
		 */
		
		
		/*
		 * FETCH TYPE EAGER ise asagidaki code session close olduktan sonra da problemsiz calisir
		 * cunku student nesnesi iliskisiyle beraber bir seferde yuklenir
		 * for (Book09 book : student.getBookList()) {
			System.out.println(book);
		}
		 */
		
		
		
		sf.close();
	}
	private static Student09 getStudent(int id) {
		Configuration con= new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student09.class).addAnnotatedClass(Book09.class);

		SessionFactory sf= con.buildSessionFactory();//oturum grubu olusturduk
		Session session=sf.openSession();//oturum grubundan bir oturum baslattik
		
		Transaction tx = session.beginTransaction();
		
		Student09 student= session.get(Student09.class, 1001);
		
		tx.commit();
		session.close();
		sf.close();
		
		return student;
		
	}

}
