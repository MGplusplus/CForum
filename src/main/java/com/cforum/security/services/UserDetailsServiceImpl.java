package com.cforum.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cforum.model.Person;
import com.cforum.repository.IPersonRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired(required=false)
	IPersonRepository personRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Person person = personRepository.findByUsername(username);
		if(person == null)
		 throw new UsernameNotFoundException("User Not Found with : " + username);
		
		return UserPrinciple.build(person);
	}

}
