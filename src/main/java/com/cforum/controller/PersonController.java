package com.cforum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cforum.request.person.UpdatePersonRequest;
import com.cforum.service.PersonService;

@RestController
@RequestMapping("/member")
public class PersonController {

	@Autowired
	private PersonService personService;


	// Get person by person id.
	@RequestMapping(value="/id/{personId}", method=RequestMethod.GET)
	public ResponseEntity<?> getPersonById(@PathVariable int personId)
	{
		return personService.getPersonById(personId);

	}

	// Edit person details.
	@RequestMapping(value="/updatePerson", method=RequestMethod.PUT)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> updatePersonDetails(@Valid @RequestBody UpdatePersonRequest personRequest )
	{
		return personService.updatePersonDetails(personRequest);
	}

}
