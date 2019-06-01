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
@Table(name="t_batch")
public class Batch {
	
	@Id
	@GeneratedValue
	@Column(name="")
	private int c_batch_id;
	
	@Column(name="c_start_date")
	private String batchStartDate;
	
	@Column(name="c_end_date")
	private String batchEndDate;

}
