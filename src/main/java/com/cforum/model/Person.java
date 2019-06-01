package com.cforum.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cforum.request.person.UpdatePersonRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="t_person")
public class Person {
	
	@Id
	@GeneratedValue
	@Column(name="c_person_id")
	private Integer personId;
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_roles", 
    joinColumns = @JoinColumn(name = "c_person_id"), 
    inverseJoinColumns = @JoinColumn(name = "c_role_id"))
    private Set<Roles> roles = new HashSet<>();
	
    // By default status id is 2;
	@Column(name="c_status_id")
	private Integer statusId = 2;
	
	@Column(name="c_firstname")
	private String firstname;
	
	@Column(name="c_middlename")
	private String middlename;
	
	@Column(name="c_lastname")
	private String lastname;
	
	@Column(name="c_username")
	private String username;
	
	@Column(name="c_cdac_conn_id")
	private Integer cdacConnId;
	
	@Column(name="c_email_id", unique=true)
	private String email;
	
	@Column(name="c_password")
	@JsonIgnore
	private String password;
	
	@Column(name="c_job_experience")
	private String jobExperience;
	
	@Column(name="c_job_year_exp")
	private Integer jobYearExp;
	
	@Column(name="c_job_tech")
	private String jobTech;
	
	@Column(name="c_mobile_no", unique=true)
	private Integer mobileNo;
	
	@Column(name="c_mobile_show_bit")
	private Integer mobileShowBit;
	
	@Column(name="c_cdac_experience")
	private String cdacExperience;
	
	@Column(name="c_batch_id")
	private Integer batchId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_person_id", referencedColumnName="c_person_id")
	@Fetch(FetchMode.JOIN)
	@JsonIgnore
	private ProfilePic profilePic;
	
	public Person(String firstname,String username, String email, String password) {
		this.firstname = firstname;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public Person(Integer personId, Integer statusId, String firstname, String middlename, String lastname,
			Integer cdacConnId, String email, String jobExperience, Integer jobYearExp, String jobTech,
			Integer mobileNo, Integer mobileShowBit, String cdacExperience, Integer batchId) {
		super();
		this.personId = personId;
		this.statusId = statusId;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.cdacConnId = cdacConnId;
		this.email = email;
		this.jobExperience = jobExperience;
		this.jobYearExp = jobYearExp;
		this.jobTech = jobTech;
		this.mobileNo = mobileNo;
		this.mobileShowBit = mobileShowBit;
		this.cdacExperience = cdacExperience;
		this.batchId = batchId;
	}

	public void updatePerson(@Valid UpdatePersonRequest personRequest) {
		this.setEmail(personRequest.getEmail());
		this.setFirstname(personRequest.getFirstname());
		this.setMiddlename(personRequest.getMiddlename());
		this.setLastname(personRequest.getLastname());
		this.setCdacExperience(personRequest.getCdacExperience());
		this.setJobExperience(personRequest.getJobExperience());
		this.setMobileNo(personRequest.getMobileNo());
		this.setMobileShowBit(personRequest.getMobileShowBit());
		this.setJobTech(personRequest.getJobTech());
		this.setCdacConnId(personRequest.getCdacConnId());
		if(personRequest.getStatusId() != null)
			this.setStatusId(personRequest.getStatusId());
		
	}

	public Person createPersonResponseObject() {
		
		return new Person(this.getPersonId(), this.getStatusId(), this.getFirstname(), 
						this.getMiddlename(),this.getLastname(), this.getCdacConnId(), this.getEmail(),
						this.getJobExperience(),this.getJobYearExp(), this.getJobTech(), this.getMobileNo(),
						this.getMobileShowBit(),this.getCdacExperience(), this.getBatchId());
	}
	
}
