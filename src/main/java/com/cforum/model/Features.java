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
@Table(name="t_features")
public class Features {
	
	@Id
	@GeneratedValue
	@Column(name="featureId")
	private int c_feature_id;
	
	@Column(name="c_feature_description")
	private String featureDescription;

}
