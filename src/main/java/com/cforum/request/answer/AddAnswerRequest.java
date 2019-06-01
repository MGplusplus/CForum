package com.cforum.request.answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class AddAnswerRequest {

	@NotNull(message = "Question Id is empty !")
	private int questionId;
	
	@NotNull(message = "Tag Id is empty !")
	@Range(min=0, max = 9, message="Invalid Tag Id !")
	private int tagId;
	
	@NotBlank(message = "Answer field is null !")
	private String answerDesc;
}
