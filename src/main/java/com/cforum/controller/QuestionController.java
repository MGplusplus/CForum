package com.cforum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cforum.request.question.AddQuestionRequest;
import com.cforum.request.question.GetQuestionRequest;
import com.cforum.service.QuestionService;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins="http://localhost:4200")
public class QuestionController {

	@Autowired
	private QuestionService questionService;	


	//Get all the recent questions.
	@RequestMapping("/allRecentQuestion")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<?> getAllRecentQues()
	{
		return questionService.getAllRecentQues();
	}
	
	// Get all the questions asked by a person
	@RequestMapping(value = "/person/{personId}", method = RequestMethod.GET)
	public ResponseEntity<?> allQuestionsOfPerson(@PathVariable int personId)
	{	
		return questionService.getAllQuestionsOfPerson(personId);
	}
	
	// Get single question using question ID.
	@RequestMapping(value = "/singleQuestion/{questionId}", method = RequestMethod.GET)
	public ResponseEntity<?> getSingleQuestion(@PathVariable int questionId)
	{
		return questionService.getSingleQuestion(questionId);
	}
	
	// Get all recently asked questions of a particular tag recently asked question should be First)
		@RequestMapping(value = "/tag/{tagId}", method=RequestMethod.GET)
		public ResponseEntity<?> recentQuestionsSameTag(@PathVariable int tagId)
		{
			return questionService.recentQuestionsSameTag(tagId);
		}
	
	// Add a question in database
	@RequestMapping(value="/addQuestion", method=RequestMethod.POST)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> addQuestion(@Valid @RequestBody AddQuestionRequest questionRequest)
	{
		return questionService.addQuestion(questionRequest);
	}
	
	
	// Get all recently asked Question in pages
	@RequestMapping(value="/recentQuestions/{pageNo}", method=RequestMethod.GET)
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<?> recentAllQuestionsInPages(@PathVariable int pageNo)
	{
		return questionService.recentQuestionsInPages(pageNo);
	}
	
	// Get all recently asked questions of a particular tag in page(descending, recently asked First)
	@RequestMapping(value = "/tag/{tagId}/{pageNo}", method=RequestMethod.GET)
	public ResponseEntity<?> recentQuestionsSameTagInPages(@PathVariable int tagId, @PathVariable int pageNo)
	{
		return questionService.recentQuestionsSameTagInPages(tagId, pageNo);
	}
	
	// Same as above but with POST request
	@RequestMapping(value = "/tag", method=RequestMethod.POST)
	public ResponseEntity<?>recentQuestionSameTagInPages(@Valid @RequestBody GetQuestionRequest questionRequest)
	{	
		return  questionService.recentQuestionSameTagInPages(questionRequest);
	}
	
}
