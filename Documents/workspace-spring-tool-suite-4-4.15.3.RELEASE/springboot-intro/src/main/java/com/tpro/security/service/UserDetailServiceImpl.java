package com.tpro.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tpro.domain.Role;
import com.tpro.domain.User;
import com.tpro.exception.ResourceNotFoundException;
import com.tpro.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired// enjecte edilmesi icin
	UserRepository userRepository;//repositorui ile iletisime gecmeliyim serviste oldugum icin

	
	//User i UserDetails e ceviriyoruz  cunku oyle istiyor securty
	//UserDetails anlayacagi bir tur cunku
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//biz user i alacagiz ki sonra UserDetails e cevirecegiz
		User user=userRepository.findByUserName(username).orElseThrow(
		()->new ResourceNotFoundException("user not found username: "+username));
		
		if (user!=null) {//user!=null cok uc ornek ama yapiyoruz yinede
			//asagida User i kullanarak UserDetails olusturmak icin S.security packet lerinden yararlaniyoruz
			return new org.
					springframework.
					security.
					core.
					userdetails.
					User(user.getUserName(),user.getPassword(),buildGrantedAuthorities(user.getRoles()));//user i gommemiz lazim
			//getUserName,getPassword, ve getRoles getrimek lazim ama getRoles S.Securitinin istedigi sekilde vermek lazim
		}else {//null olma durumu kontrolu
			throw new UsernameNotFoundException("User not found username:"+username);
			//UsernameNotFoundException hazir var 
		}
		
	}
	
	//role ozelligi security katmaninda simpleGrantedAuthority yapisinda olmasi gerekiyor
	private static List<SimpleGrantedAuthority> buildGrantedAuthorities(final Set<Role>roles){
		List<SimpleGrantedAuthority> authorities= new ArrayList<>();
		for(Role role:roles) {//rol leri for each ile geziyoruz
			//role enum yapisinda oldugu icin getName().name() yazdik
			authorities.add(new SimpleGrantedAuthority(role.getName().name()));
			//(role.getName().name()) enum turunde oldugu icin boyle yaziliyor
		}
		return authorities;
	}

}
