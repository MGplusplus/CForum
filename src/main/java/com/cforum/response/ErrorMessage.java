package com.cforum.response;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorMessage {
	
	@CreationTimestamp
	private Date timestamp;
	private int status = 500;
	private String error = "Internal Serve Error !";
	private String message = "Unsuccessfull !";
	private String path;

}
