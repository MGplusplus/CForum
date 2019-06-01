package com.cforum.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cforum.model.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrinciple implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 437815695453984396L;

	private Integer personId;
	
	private String name;
	
	private String username;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	

	public UserPrinciple(Integer id, String name, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.personId = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserPrinciple build (Person person)
	{
		List<GrantedAuthority> authorities = person.getRoles().stream().map(roles -> 
			new SimpleGrantedAuthority(roles.getName().name())
				).collect(Collectors.toList());
		
		return new UserPrinciple(
				person.getPersonId(),
				person.getFirstname(),
				person.getUsername(),
				person.getEmail(),
				person.getPassword(),
				authorities
				);
		
	}
	
	public Integer getPersonId() {
        return personId;
    }
 
    public String getName() {
        return name;
    }
 
    public String getEmail() {
        return email;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        
	        UserPrinciple user = (UserPrinciple) o;
	        return Objects.equals(personId, user.personId);
	    }

}
