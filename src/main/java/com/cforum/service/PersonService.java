package com.cforum.service;

import java.util.Date;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.cforum.exception.NotFoundException;
import com.cforum.exception.UnAuthorizeRequestException;
import com.cforum.model.Person;
import com.cforum.repository.IPersonRepository;
import com.cforum.request.person.UpdatePersonRequest;
import com.cforum.response.ErrorMessage;
import com.cforum.security.services.UserPrinciple;
import com.cforum.utility.IPersonResponse;

@Component
public class PersonService {

	@Autowired
	private IPersonRepository personRepository;
	
	Logger logger = LoggerFactory.getLogger(PersonService.class);

	public ResponseEntity<?> getPersonById(Integer personId) {
		
		IPersonResponse person = null;
		try
		{
			person = personRepository.findFirstByPersonId(personId);

		} catch(Exception e)
		{
			logger.error("Unable to fetch person of personId: "+personId+" from database !(ps) "+"  Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(person == null || person.getStatusId() != 2)
			throw new NotFoundException("User not exist of id : "+ personId);
		
		return ResponseEntity.ok().body(person);
		
	}

	public ResponseEntity<?> updatePersonDetails(@Valid UpdatePersonRequest personRequest) {
		
		UserPrinciple user = null;
		Person person = null;
		
		try
		{
			user = (UserPrinciple) SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
		
			if(personRequest.getPersonId() == user.getPersonId() ||
									user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			{	
				// get person data from database without actually hitting database every time.
				person = personRepository.getOne(personRequest.getPersonId());
			
				//updating existing person details with the new information
				person.updatePerson(personRequest);
			
				// save person with new information
				personRepository.save(person);
				
				return ResponseEntity.ok().body(person.createPersonResponseObject());
			}
			else
			{
				logger.info("Unauthorize attempt request to change answer through person Id : "+ personRequest.getPersonId());
				
				throw new UnAuthorizeRequestException("Forbidden");
			}
			
		} catch (EntityNotFoundException e)
		{
				logger.error("unable to find person (ps) answer Id: "+personRequest.getPersonId()+
							",   Stack Trace : " + e.getStackTrace());

				return new ResponseEntity<>(new ErrorMessage(new Date(), 404, e.getMessage(), "Not Found", ""),
							new HttpHeaders(), HttpStatus.NOT_FOUND);
		} catch (Exception e)
		{
			logger.error("Unable to update person data of person Id : "+personRequest.getPersonId()+
					"Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
