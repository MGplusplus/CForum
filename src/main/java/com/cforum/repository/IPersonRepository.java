package com.cforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cforum.model.Person;
import com.cforum.utility.IPersonResponse;

public interface IPersonRepository extends JpaRepository<Person, Integer>{

	// called from UserDetailsServiceImpl from security services package.
	Person findByUsername(String username);

	// called from AuthRestApi class from controller package.
	boolean existsByEmail(String username);

	IPersonResponse findFirstByPersonId(Integer personId);

}
