package com.tpro.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwtToken=parseJwt(request);
		//requestin icinden tokeni alacagim 
		try {
			if(jwtToken!=null && jwtUtils.validateToken(jwtToken) ) {
				String userName = jwtUtils.getUserNameFromJwtToken(jwtToken);
				UserDetails userDetails= userDetailsService.loadUserByUsername(userName);//burayada securty contex e kulllanici bilgilerini gonderdik
				UsernamePasswordAuthenticationToken authentication=
						new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (UsernameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//yukarda yaptiklarimizi eklememiz lazim
		filterChain.doFilter(request, response);
		
	}
	private String parseJwt(HttpServletRequest request) {
		//request in header kismindaki Authorization
		String header=request.getHeader("Authorization");
		if(StringUtils.hasText(header)&&header.startsWith("Bearer ")) {
			return header.substring(7);
		}
		return null;
	}
	
	@Override   // burada gelen entpoint e gore islem yapilip yapilmayacagini soyluyoruz / serbes dolasim alani gibi
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		//bunlari filitrelemeye tabi tutma bunlar kayitsiz kullanicilar
		AntPathMatcher antMather=new AntPathMatcher();
		return antMather.match("/register",request.getServletPath())|| 
				antMather.match("/login",request.getServletPath());
	}
	

}
