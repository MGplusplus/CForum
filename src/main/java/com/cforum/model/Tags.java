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
@Table(name="t_tags")
public class Tags {
	
	@Id
	@GeneratedValue
	@Column(name="c_tag_id")
	private int tagId;
	
	@Column(name="c_tag")
	private String tag;

}
