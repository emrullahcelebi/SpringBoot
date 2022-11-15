package com.hb14.entity_life_cycle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class RunnerSave14 {

	public static void main(String[] args) {
		
		Student14 student1=new Student14();
		student1.setName("John Coffee");
		student1.setmathGrade(10);
		
		Student14 student2=new Student14();
		student2.setName("Jemes Bond");
		student2.setmathGrade(11);
		
		Student14 student3=new Student14();
		student3.setName("Tony Stark");
		student3.setmathGrade(9);
		
		Configuration con= new Configuration().configure("hibernate.cfg.xml").
							addAnnotatedClass(Student14.class);

		SessionFactory sf= con.buildSessionFactory();//oturum grubu olusturduk
		Session session=sf.openSession();//oturum grubundan bir oturum baslattik
		
		Transaction tx = session.beginTransaction();
			
			//session.save(student1);
			//student1 save edildikten sonra persisten state geciyor
			
			//student1.setName("Updated in persistent state");
			
			//detached yapinca yukaridaki save methodunun yaptigi degisiklikte kalici olmuyor
			//session.evict(student1);
			
			//detached yapinca asagidaki set ise yaramiyor. 
			//cunku artik persistent context tarafindan detach edilmis.
			
			//student1.setName("Updated in detached");
	
			//session.update(student1);
			//session.merge(student1);
		
		//delete methodu ile silinecegini soyluyoruz daha sonra commit ile vt den kalici olarak siliniyor 
			Student14 studentFound= session.load(Student14.class,1L);
			session.delete(studentFound);
			
			
		tx.commit();
		session.close();
		sf.close();
	}

}
