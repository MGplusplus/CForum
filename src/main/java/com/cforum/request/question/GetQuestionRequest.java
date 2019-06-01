package com.cforum.request.question;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;



public class GetQuestionRequest {
	
	@NotNull(message = "Page no is empty !")
	@Range(min=0, message="Invalid page number !")
	private int pageNo;
	
	@NotNull(message = "Tag Id is empty !")
	@Range(min=0, max = 9, message="Invalid Tag Id !")
	private int tagId;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	
	

}
