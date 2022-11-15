package crudProje;

public class Main {

	public static void main(String[] args) {
	
		//Crud islemleri icin gerekli olan class dan nesnelerin turetilmesi
		CrudMetodlar metot=new CrudMetodlar();
		
		//tum CRUD islemleri session factory olusturan methodu calistir
		metot.sessionFactoryOlustur();
		
		//veri tabanina yeni personeller ekle
//		metot.personelEkle("Ahmet", "Yilmaz", 5500);
//		metot.personelEkle("Mustafa", "Baki", 7200);
//		metot.personelEkle("Can", "Ali", 4450);
	
		
		metot.idIlePersonelSil(2L);//2 yi long olarak yazdik
		metot.idIlePersonelSil(4L);
		
		
		metot.tumPersoneliListele();
		metot.idIlePersonelListele(3L);
		metot.idIleMaasGuncelle(3L, 8000);
		metot.tumPersoneliListele();
	}

}
