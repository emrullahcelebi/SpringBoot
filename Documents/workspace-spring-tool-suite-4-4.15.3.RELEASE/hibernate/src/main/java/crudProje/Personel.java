package crudProje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity    //tablo olusturmak icin
@Table(name="personeller")   //tabloya yeniden kendi istedigimiz isi=mi vermek icin
public class Personel {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)//otomatik artis yapacak
	private long id;
	private String ad;
	private String soyad;
	private int maas;
	
	public Personel() {
		
	}
	
	public Personel(/*int id,*/ String ad, String soyad, int maas) {
		//super();  --->extend edilmis bir class olmadigi icin silinebilir
		//this.id = id; ---> otomatik id verecegi icin buna gerek yokk
		this.ad = ad;
		this.soyad = soyad;
		this.maas = maas;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public int getMaas() {
		return maas;
	}

	public void setMaas(int maas) {
		this.maas = maas;
	}

	@Override
	public String toString() {
		return "Personel id=" + id + ", ad=" + ad + ", soyad=" + soyad + ", maas=" + maas;
	}
	

}
