package com.tpro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration//konfigurasyon yapacağımı söylüyorum. bu class config class'ı
@EnableWebSecurity// web sec. aktif ediyoruz
@EnableGlobalMethodSecurity(prePostEnabled = true)//security'i metod bazlı olarak çalışmak istediğimiz için koyduk, yazmasaydık metod bazlı çalışamazdık
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired //Service katmani ile irtibata gecmeliyim
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//method zinciri olusturuyoruz
		
	http.csrf().//api ürettiğimiz için crsf'e gerek kalmıyor çünkü sadece get yaptırıyor.put vs yaptırmıyor guvenligi ben sagliyacagiz  	
	disable().//default olarak enabled biz disable yaptık
	authorizeHttpRequests().//önce bi authorized et yetki gelen butun request leri kontrol et
	antMatchers("/","index.html","/css/*","/buraya istediğimiz end pointi yazabiliriz").//bu endpointlerle gelirsen girebilirsin auth gerek yok
	permitAll().//bunları muaf tut(yukardaki endpointleri), yani security uygulama
	anyRequest().//ama bunların dışında ne gelirse gelsin asagida authendicate et
	authenticated().//auth etceksin. kimsin?
	and().httpBasic();    //  sec. seviyesi ve bunları basic auth ile yap..artık her requeste kullanıcı adı ve şifresini vermesi lazım ve decode etmesi lazım.		
	}//and oncesini yap sonra da benden sonrakini yap der gibi
	
	/*
	
	//inMemory olarak userları oluşturuyoruz. program kapandığında gidecek Hazir method
	@Override
	@Bean//aşağıdaki metodu biz yazmadık dışardan aldık o yüzcden ioc'ye atmamız lazım
	protected UserDetailsService userDetailsService() {//userleri belli bir formatta oluşturcaz
		
		UserDetails userEmin = User.builder().//User--> spring security core 'dan geliyor
				username("emin").
				password(passwordEncoder().encode("emin")).//encode etmemiz lazım onu da aşağıdaki metodda yaptık
				roles("ADMIN").//rolu ney 
				build();	//olustur
		
		UserDetails userAlvia = User.builder().//User insa et diyoruz
				username("alvia").password(passwordEncoder().encode("alvia")).
				roles("STUDENT").
				build();//2.user oluşturduk
		UserDetails userIbrahim = User.builder().
				username("ibrahim").password(passwordEncoder().encode("ibrahim")).
				roles("STUDENT","ADMIN").
				build();//birden fazla yetki verebiliyoruz.
		
		return new InMemoryUserDetailsManager(new UserDetails[] {userEmin,userAlvia,userIbrahim});//InMemoryUserDetailsManager metodu üzerinden UserDetailsleri giriyoruz
		//burada hazir method kullaniyoruz "InMemoryUserDetailsManager" gecici obje gibi cparametreli const. gibi yaziyoruz
	}	
		*/
	
	//Maneger i insa edecegiz
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());//Provider olusturuyoruz patron Maneger is yapmiyor pasam
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(10);//neden 10 kere dedik kaçırdım burayı sor.
	}
	
	
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		//kullanici bilgileri
		authProvider.setUserDetailsService(userDetailsService);
		//kullanilacak encoder-decoder methodu belirliyoruz
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
}