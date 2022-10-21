package com.lib.security.service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lib.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {//Spring Security nin istedigi UserDetails oldugu icin 
	
	private Long id;
	
	private String userMail;
	
	@JsonIgnore //bu on tarafa gitmesin diye
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	// loadUserByUserName kısmında kullanmak içi build() üretıyorum
	public static UserDetailsImpl build(User user) {
		List<SimpleGrantedAuthority> authorities =
				user.getRoles().stream().
				map(role->new SimpleGrantedAuthority(role.getName().name())).
				collect(Collectors.toList());
		return new UserDetailsImpl(user.getId(), 
								   user.getUserMail(), 
								   user.getPassword(), 
								   authorities);
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userMail;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}