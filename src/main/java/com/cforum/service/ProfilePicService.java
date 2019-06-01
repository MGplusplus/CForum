package com.cforum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cforum.repository.IProfilePicRepository;
import com.cforum.utility.ICompProfilePic;

@Component
public class ProfilePicService {

	@Autowired
	private IProfilePicRepository profilePicRepository;
	
	public ICompProfilePic fetchCompImage(int personId) {
		return profilePicRepository.findFirstByPersonId(personId);
	}

}
