package com.cforum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.cforum.model.enums.RoleName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="t_roles")
public class Roles {
	
	@Id
	@GeneratedValue
	@Column(name="c_role_id")
	private int roleId;
	
	@Enumerated(EnumType.STRING)
    @NaturalId
	@Column(name="c_role_description")
	private RoleName name;
	
	public Roles(RoleName name) {
        this.name = name;
    }

}
