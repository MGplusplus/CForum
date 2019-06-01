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
@Data
@Entity
@Table(name="t_status")
public class Status {
	
	@Id
	@GeneratedValue
	@Column(name="c_status_id")
	private int statusId;
	
	@Column(name="c_status")
	private String status;

}
