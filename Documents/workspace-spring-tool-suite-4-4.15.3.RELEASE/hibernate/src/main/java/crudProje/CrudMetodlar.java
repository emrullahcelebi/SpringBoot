package crudProje;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class CrudMetodlar {

	private static SessionFactory factory;//diger yerlerde kullanabilmek icin global yaptik
	
	public void sessionFactoryOlustur() {
	try {
	Configuration con= new Configuration().configure("hibernate.cfg.xml").
			addAnnotatedClass(Personel.class);

	factory = con.buildSessionFactory();//oturum grubu olusturduk	
	
	}catch (Throwable e){//olusturamaz ise burasi calisacak
		System.err.println("Session grubu olusturulurken hata olustu "+e);
		throw new ExceptionInInitializerError(e);//initialize etmezse bu hata cikacagi icin --gormek istedigimiz hatanin turu
	}
	
}
	
	
	
	//Veri tabanina bir personel ekleyen method(Create)
	public Long personelEkle(String ad, String soyad, int maas) {
		Long personelId=null;//0 atasak o bir bilgi olur
		Session session = factory.openSession();//oturum grubundan bir oturum baslattik
		Transaction tx=null;//try-catch blogunun disinda atama yapiyoruz ---okumak icin
		//ya yapamaz ise bu islemleri diye try-catch
		try {
			 tx= session.beginTransaction();
		Personel personel = new Personel(ad, soyad, maas);//objeyi olusturduk artik girilen bilgiler burada
		personelId= (Long) session.save(personel);//kaydediyoruz kaydettigini gormek istiyorsak yazdiridriz bize id doner
		
		tx.commit();//islemleri aktarmak icin
		
		}catch(HibernateException e) {
			//yapilan islemde bir hata oldu ise islemi geri al
			if (tx!=null) {//veri gonderilince null oluyor
				tx.rollback();//islemi geri al
			}
			e.printStackTrace();
			
		}finally {//   !!!her halikarda calisacak 
			session.close();//bunu da clos icin kullanacagiz
		}
		
		return personelId; 
		
		
	}
	
	//Veri tabannindan personel silen method(DELETE)
	
	public void idIlePersonelSil(Long personelId) {
		Session session=factory.openSession();
		Transaction tx=null;//her bir islem icin yapmaliyiz
		
		try {
			tx =session.beginTransaction();//dis kaynak oldugu icin
			Personel personel= session.get(Personel.class, personelId);//burasi bize personel getirecek
			
			if (personel==null) {
				System.out.println(personelId+ " nolu kisi kaydi bulunamamistir.");
			}else {
				session.delete(personel);
				tx.commit();
				System.out.println(personelId+ " nolu kisi kaydi silinmistir.");
			}
				
		}catch(HibernateException e) {
			if (tx!=null) {//veri gonderilince null oluyor
				tx.rollback();//islemi geri al
			}
			e.printStackTrace();//hatayi bize yazdir
		}finally {
		session.close();	//her turlu durumda session u kapat
		}
		
		
		
	}
	
	
	//Veri tabanindaki tum kayitlari listeleyen metot(READ)
	public void tumPersoneliListele() {
		Session session= factory.openSession();
		Transaction tx=null;
		
		try {
			tx=session.beginTransaction();
			List<Personel> personeller=session.createQuery("FROM Personel").getResultList();//hql olarak 
			
			System.out.println("******* TUM PERSONELIN LISTESI *******");
			personeller.stream().forEach((p)->System.out.println(p));
			
			tx.commit();
			
		}catch(HibernateException e) {
			if (tx!=null) {//veri gonderilince null oluyor
				tx.rollback();//islemi geri al
			}
			e.printStackTrace();//hatayi bize yazdir
		}finally {
		session.close();	//her turlu durumda session u kapat
		}
	}
	
	
	//Veri tabanindaki id ile bir kaydin bilgilerini listeleyen metot(READ)
		public void idIlePersonelListele(Long personelId ) {
			Session session= factory.openSession();
			Transaction tx=null;
			
			try {
				tx=session.beginTransaction();
				Personel personel=session.get(Personel.class, personelId);
				
				System.out.println("*******"+ personelId+ " ID li Personel "+"*******");
				if (personel==null) {
					System.out.println(personelId+ " nolu kisi kaydi bulunamamistir.");
				}else {
					tx.commit();
					System.out.println(personel);
				}

			}catch(HibernateException e) {
				if (tx!=null) {//veri gonderilince null oluyor
					tx.rollback();//islemi geri al
				}
				e.printStackTrace();//hatayi bize yazdir
			}finally {
			session.close();	//her turlu durumda session u kapat
			}
		}
		//Veri tabanindaki id ile bir kaydin maas bilgilerini guncelleyen metot(READ)
				public void idIleMaasGuncelle(Long personelId,int maas ) {
					Session session= factory.openSession();
					Transaction tx=null;
					
					try {
						tx=session.beginTransaction();
						Personel personel=session.get(Personel.class, personelId);
						
						System.out.println("*******"+ personelId+ " ID li Personelin Maas Guncellemesi "+"*******");
						if (personel==null) {
							System.out.println(personelId+ " nolu kisi kaydi bulunamamistir.");
						}else {
							personel.setMaas(maas);
							tx.commit();
							System.out.println(personelId+" nolu personelin yeni maasi: "+maas);
						}

					}catch(HibernateException e) {
						if (tx!=null) {//veri gonderilince null oluyor
							tx.rollback();//islemi geri al
						}
						e.printStackTrace();//hatayi bize yazdir
					}finally {
					session.close();	//her turlu durumda session u kapat
					}
				}
	
}
