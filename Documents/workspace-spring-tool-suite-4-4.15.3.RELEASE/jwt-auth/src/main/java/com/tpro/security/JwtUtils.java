package com.tpro.security;
import java.util.Date;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.tpro.security.service.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JwtUtils {
	
	private String jwtSecret = "batch82";//JWT icin gerekli iceride bir secret yapmamiz lazim
	
	// 24*60*60*1000// 1 gun e esit bu ms. cinsinden
	private long jwtExpirationMs = 86400000;//JWT icin guvenlik suresi tekrarlama yeni token icin
	
	//************* GENRERATE -- TOKEN  ************
	//token uretiyoruz
	public String generateToken(Authentication authentication) {//String olmasi -> math. islem olmayacak
		
		// anlık olarak login olarak kullanıcının bilgisini alıyorum. cunku UserName ye ihtiyacim var
		    UserDetailsImpl userDetails  = (UserDetailsImpl) authentication.getPrincipal();//getPrincipal kullanici bilgilerini getiriyor
		 // Token builder() ile üretiliyor
		  // token üretilirken UserName ve secret key kullanılıyor
		 return Jwts.builder() .setSubject(userDetails.getUsername()).//username yi getirdik
				 										setIssuedAt(new Date()).
				 										setExpiration(new Date(new Date().getTime()+ jwtExpirationMs)).//su andaki vakite yukarida olusturdugumuz saati ekleyecegiz
				 										signWith(SignatureAlgorithm.HS512, jwtSecret).//buraya da sifreleme algoritma methodunu ekliyoruz ve Secret imizi ekliyoruz
				 										compact();//topla bunlari
	}
	
	//***************************************************
	
	
	
	
	//******************* VALIDATE-TOKEN**************
	public boolean validateToken(String token) {//ture veya false
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedJwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedJwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//**********************************************
	
	//********** JWt tokenden userName'i alalım
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
}