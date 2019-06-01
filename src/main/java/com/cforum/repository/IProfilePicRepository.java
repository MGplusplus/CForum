package com.cforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cforum.model.ProfilePic;
import com.cforum.utility.ICompProfilePic;

public interface IProfilePicRepository extends JpaRepository<ProfilePic, Integer>{
	
	ICompProfilePic findFirstByPersonId(int i);

}
