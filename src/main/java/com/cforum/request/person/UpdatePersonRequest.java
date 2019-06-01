package com.cforum.request.person;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdatePersonRequest {
	
	@NotNull(message="Person id ie empty !")
	private Integer personId;
	
	private Integer statusId = 2;
	
	@NotBlank(message="Firstname is empty !")
	private String firstname;
	
	private String middlename;
	
	private String lastname;
	
	private Integer cdacConnId;
	
	@NotBlank(message="email is empty !")
	private String email;
	
	private String jobExperience;
	
	private Integer jobYearExp;
	
	private String jobTech;
	
	private Integer mobileNo;
	
	private Integer mobileShowBit;
	
	private String cdacExperience;
	
	private Integer batchId;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getCdacConnId() {
		return cdacConnId;
	}

	public void setCdacConnId(Integer cdacConnId) {
		this.cdacConnId = cdacConnId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobExperience() {
		return jobExperience;
	}

	public void setJobExperience(String jobExperience) {
		this.jobExperience = jobExperience;
	}

	public Integer getJobYearExp() {
		return jobYearExp;
	}

	public void setJobYearExp(Integer jobYearExp) {
		this.jobYearExp = jobYearExp;
	}

	public String getJobTech() {
		return jobTech;
	}

	public void setJobTech(String jobTech) {
		this.jobTech = jobTech;
	}

	public Integer getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Integer mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Integer getMobileShowBit() {
		return mobileShowBit;
	}

	public void setMobileShowBit(Integer mobileShowBit) {
		this.mobileShowBit = mobileShowBit;
	}

	public String getCdacExperience() {
		return cdacExperience;
	}

	public void setCdacExperience(String cdacExperience) {
		this.cdacExperience = cdacExperience;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	
	

}
