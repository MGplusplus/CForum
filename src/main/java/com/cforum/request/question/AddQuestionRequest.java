package com.cforum.request.question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class AddQuestionRequest {

	@NotNull(message = "Tag Id is empty !")
	@Range(min=0, max = 9, message="Invalid Tag Id !")
	private int tagId;
	
	@NotBlank(message = "Question field is null !")
	private String questionDesc;

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

}
