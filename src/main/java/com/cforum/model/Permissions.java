package com.cforum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="t_permissions")
public class Permissions {
	
	@Id
	@GeneratedValue
	@Column(name="c_perm_id")
	private int permissionId;
	
	@Column(name="c_role_id")
	private int roleId;
	
	@Column(name="c_feature_id")
	private int featureId;
	
	@Column(name="c_read")
	private int read;
	
	@Column(name="c_write")
	private int write;
	
	@Column(name="c_update")
	private int update;
	
	@Column(name="c_non_active")
	private int nonActive;
	
	@Column(name="c_delete")
	private int delete;

}
