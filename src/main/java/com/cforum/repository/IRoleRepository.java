package com.cforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cforum.model.Roles;
import com.cforum.model.enums.RoleName;

public interface IRoleRepository extends JpaRepository<Roles, Integer>{
	Roles findByName(RoleName roleName);
	
}
