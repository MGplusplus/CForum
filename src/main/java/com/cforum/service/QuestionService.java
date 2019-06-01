package com.cforum.service;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.cforum.exception.NotFoundException;
import com.cforum.model.Question;
import com.cforum.repository.IQuestionRepository;
import com.cforum.request.question.AddQuestionRequest;
import com.cforum.request.question.GetQuestionRequest;
import com.cforum.response.ErrorMessage;
import com.cforum.security.services.UserPrinciple;


@Component
public class QuestionService {

	@Autowired
	IQuestionRepository questionRepository;

	Logger logger = LoggerFactory.getLogger(QuestionService.class);

	public ResponseEntity<?> recentQuestionsInPages(int pageNo)
	{
		Iterable<Question> question = null;
		try
		{
			question =  questionRepository.findAll(gotoPageDesc(pageNo));
		}
		catch (Exception e)
		{
			logger.error("Unable to fetch recent questions page of page no: "+pageNo+" from database !(qs) "+"Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok().body(question);
	}


	public ResponseEntity<?> recentQuestionSameTagInPages(@Valid GetQuestionRequest questionRequest) 
	{	
		Page<Question> page = null;

		try 
		{
			// fetch data from request
			int pageNo = questionRequest.getPageNo();
			int tagId = questionRequest.getTagId();

			// get data from database in pages.
			page =  questionRepository.findByTagIdOrderByQuestionId(tagId, gotoPageDesc(pageNo));
		} catch (Exception e)
		{
			logger.error("Unable to fetch questions page of tag ID:"+questionRequest.getTagId()+
					" from database !(qs) "+"Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok().body(page);
	}

	public ResponseEntity<?> recentQuestionsSameTagInPages(int tagId, int pageNo) {

		Page<Question> page = null;

		try
		{
			page =  questionRepository.findByTagIdOrderByQuestionId(tagId, gotoPageDesc(pageNo));
		} catch (Exception e)
		{
			logger.error("Unable to fetch questions page of tag ID: "+tagId+"Page no: "+pageNo+
					" from database !(qs) "+"Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok().body(page);

	}


	public ResponseEntity<?> getAllQuestionsOfPerson(Integer personId) {

		Iterable<Question> questions = null;

		try
		{
			questions = questionRepository.findByPersonIdOrderByQuestionIdDesc(personId);
		} catch(Exception e)
		{
			logger.error("Unable to fetch questions of user from database !(qs) "+
					"  Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		if(questions == null)
		{
			logger.info(" No questions found of person with person id: "+ personId);
			throw new NotFoundException("No data found");
		}
		return ResponseEntity.ok().body(questions);
	}

	// Paging logic -> return sorted data, Recently asked question first.(descending order)
	private Pageable gotoPageDesc(int page)
	{
		Pageable pageable = PageRequest.of(page, 2, Sort.by("questionId").descending());
		return pageable;
	}

	// Add a Question in database not properly implemented yet also not tested
	public ResponseEntity<?> addQuestion(@Valid AddQuestionRequest questionRequest) {

		UserPrinciple user = null;
		Question question = null;

		try
		{
			user = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			question = new Question(user.getPersonId(), questionRequest.getTagId(), questionRequest.getQuestionDesc());	

			questionRepository.save(question);
		} catch (Exception e)
		{
			logger.error("Unable to save question !(qs) "+"Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok().body("Succesfully Added");
	}


	public ResponseEntity<?> getSingleQuestion(int questionId) {

		Question question = null;

		try
		{
			question = questionRepository.findByQuestionId(questionId);
		} catch (Exception e)
		{
			logger.error("Unable to fetch single question !(qs) with Question Id : "+questionId+
					"  Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(question == null)
		{
			logger.info("unable to fetch single question due to invalid question id: "+ questionId);
			throw new NotFoundException("Invalid Question Id !");
		}

		return ResponseEntity.ok().body(question);
	}


	public ResponseEntity<?> getAllRecentQues() {

		Iterable<Question> questions = null;

		try
		{
			questions = questionRepository.findAllByOrderByQuestionIdDesc();
		} catch (Exception e)
		{
			logger.error("unable to ftech list of recent questions !(qs) " +"  Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok().body(questions);
	}


	public ResponseEntity<?> recentQuestionsSameTag(int tagId) {
		Iterable<Question> questions = null;
		
		questions =questionRepository.findAllByTagIdOrderByQuestionIdDesc(tagId);
		
		return ResponseEntity.ok().body(questions);
	}

}
