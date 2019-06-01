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
@Table(name="t_profile_pic")
public class ProfilePic {

	@Id
	@GeneratedValue
	@Column(name="c_profile_pic_id")
	private int profilePicId;

	@Column(name="c_person_id")
	private int  personId;
	
	@Column(name="c_profile_pic_comp")
	private byte[] compPropfilePic;
	
	@Column(name="c_profile_pic_original")
	private byte[] originalProfilePic;

	public ProfilePic(int id, byte[] pic)
	{
		this.profilePicId = 1;
		this.personId = id;
		this.compPropfilePic = pic;
	}
}
