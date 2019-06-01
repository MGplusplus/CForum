package com.cforum.request.answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EditAnswerRequest {

	@NotNull(message = "Answer Id is empty !")
	private int answerId;
	
	@NotBlank(message = "Answer field is Empty !")
	private String answerDesc;
}
