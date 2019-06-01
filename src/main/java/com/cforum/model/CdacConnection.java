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
@Table(name="t_cdac_connection")
public class CdacConnection {
	
	@Id
	@GeneratedValue
	@Column(name="c_cdac_conn_id")
	private int cdacConnId;
	
	@Column(name="c_cdac_conn_description")
	private String cdacConnDescription;

}
