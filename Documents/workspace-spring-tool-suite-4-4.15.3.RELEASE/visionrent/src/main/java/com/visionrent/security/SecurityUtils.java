package com.visionrent.security;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
	// controller katmaninda anlik olarak login olan kullaniciya ihtiyacim olursa buradaki metodu kullanayim
	

	public static Optional<String> getCurrentUserLogin() {
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		
		Authentication authentication =  securityContext.getAuthentication();
		
		return Optional.ofNullable(extractPrincipal(authentication));
		
		
	}
	
	private static String extractPrincipal(Authentication authentication) {
		if(authentication == null) {//boyle biri yok ise
			
		   return null;
		   
		}else if(authentication.getPrincipal() instanceof UserDetails) {//instanceof UserDetails donen deger UserDetails mi diyor
			
		UserDetails secureUser	= (UserDetails) authentication.getPrincipal();
		return secureUser.getUsername();
		
		}else if(authentication.getPrincipal() instanceof String) {
			
			return (String) authentication.getPrincipal();
		}
		
		return null;
			
	}
	
}
