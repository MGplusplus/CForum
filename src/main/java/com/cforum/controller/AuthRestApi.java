package com.cforum.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cforum.model.Person;
import com.cforum.model.Roles;
import com.cforum.model.enums.RoleName;
import com.cforum.repository.IPersonRepository;
import com.cforum.repository.IRoleRepository;
import com.cforum.request.LoginForm;
import com.cforum.request.SignUpForm;
import com.cforum.response.JwtResponse;
import com.cforum.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestApi {
	
	@Autowired
    AuthenticationManager authenticationManager;
 
    @Autowired
    IPersonRepository personRepository;
 
    @Autowired
    IRoleRepository roleRepository;
 
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest)
    {
   
    	Authentication authentication = authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
    			);
    	
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	String jwt = jwtProvider.generateJwtToken(authentication);
    	return ResponseEntity.ok(new JwtResponse(jwt));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest)
    {
    	if(personRepository.existsByEmail(signUpRequest.getUsername()))
    	{
    		return new ResponseEntity<String>("Fail -> Username is already taken! ", HttpStatus.BAD_REQUEST);
    	}
    	
    	// creating user's account
    	
    	Person person = new Person(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
    								encoder.encode(signUpRequest.getPassword()));
    	
    	Set<String> strRoles = signUpRequest.getRole();
    	Set<Roles> roles = new HashSet<>();
    	
    	strRoles.forEach(role -> {
    		switch (role) {
			case "root":
				
				Roles adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
				if(adminRole == null)
					throw new RuntimeException("Fail ! -> Cause :User Role not find.");
				roles.add(adminRole);
				break;

			default:
				Roles userRole = roleRepository.findByName(RoleName.ROLE_USER);
				if(userRole == null)
					throw new RuntimeException("Fail ! -> Cause :User Role not find.");
				roles.add(userRole);
				break;
			}
    	});
    	
    	person.setRoles(roles);
    	personRepository.save(person);
    	
    	return ResponseEntity.ok().body("User registered Successfully !");
    }

}
