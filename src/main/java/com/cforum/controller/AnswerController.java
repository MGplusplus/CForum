package com.cforum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cforum.request.answer.AddAnswerRequest;
import com.cforum.request.answer.EditAnswerRequest;
import com.cforum.service.AnswerService;

@RestController
@RequestMapping("/answer")
public class AnswerController {
	
	@Autowired
	AnswerService answerService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> addAnswer(@Valid @RequestBody AddAnswerRequest answerRequest)
	{	
		return answerService.addAnswer(answerRequest);
	}
	
	@RequestMapping(value="/getAnswer/{answerId}", method=RequestMethod.GET)
	public ResponseEntity<?> getanswerById(@PathVariable int answerId)
	{
		return answerService.getAnswerByAnsId(answerId);
	}
	
	@RequestMapping(value="/allAnswer/{questionId}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllAnsByQuesId(@PathVariable int questionId)
	{
		return answerService.getAllAnsByQuesId(questionId);
	}
	
	@RequestMapping(value="/editAnswer", method=RequestMethod.PUT)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> editAnswer(@Valid @RequestBody EditAnswerRequest answerRequest)
	{
		return answerService.editAnswer(answerRequest);
	}

  }
