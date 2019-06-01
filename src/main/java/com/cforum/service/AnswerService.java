package com.cforum.service;

import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.cforum.exception.NotFoundException;
import com.cforum.exception.UnAuthorizeRequestException;
import com.cforum.model.Answer;
import com.cforum.repository.IAnswerRepository;
import com.cforum.request.answer.AddAnswerRequest;
import com.cforum.request.answer.EditAnswerRequest;
import com.cforum.response.ErrorMessage;
import com.cforum.security.services.UserPrinciple;

@Component
public class AnswerService {
	
	@Autowired
	IAnswerRepository answerRepository;
	
	Logger logger = LoggerFactory.getLogger(AnswerService.class);

	public ResponseEntity<?> addAnswer(@Valid AddAnswerRequest answerRequest) {
		
		UserPrinciple user = null;
		Answer answer = null;
		
		try
		{
			user = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			answer = new Answer(answerRequest.getQuestionId(), user.getPersonId(), answerRequest.getTagId(),
									answerRequest.getAnswerDesc());
		
			answerRepository.save(answer);
		} catch (Exception e)
		{
			logger.error("Unable to save answer !(as) "+"Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok().body("Answer Successfully added !");
	}

	public ResponseEntity<?> getAnswerByAnsId(int answerId) {
		
		Optional<Answer> answer = null;
		
		try
		{
			answer = answerRepository.findById(answerId);
		} catch (Exception e)
		{
			logger.error("Unable to get answer !(as) answer Id : "+answerId+",  Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(answer == null)
		{
			logger.info(" No answer found of answer id: "+ answerId);
			throw new NotFoundException("No answer found of answer id: "+ answerId);
		}
		return ResponseEntity.ok().body(answer);
	}

	public ResponseEntity<?> getAllAnsByQuesId(int questionId) {
		Iterable<Answer> answer = null;
		
		try
		{
			answer = answerRepository.findByQuestionIdOrderByAnswerIdDesc(questionId);
		}  catch (Exception e)
		{
			logger.error("Unable to get all the answers !(as) for question Id : "+questionId+",  Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		if(answer == null)
		{
			logger.info(" No answer found of question id: "+ questionId);
			throw new NotFoundException("No answer found of question id: "+ questionId);
		}
		return ResponseEntity.ok().body(answer);
	}

	public ResponseEntity<?> editAnswer(@Valid EditAnswerRequest answerRequest) {
		
		Answer answer = null;
		UserPrinciple user = null;
		
		try
		{
			user = (UserPrinciple) SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			
			answer = answerRepository.getOne(answerRequest.getAnswerId());
		
			if(answer.getPersonId() == user.getPersonId() || 
									user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			{	
				answer.setAnswer(answerRequest.getAnswerDesc());
			
			//	need to create new answer object in response as the existing answer object have different extra things in it.
			// 	passing existing answer object directly lead to an error in response body.
				return ResponseEntity.ok().body(new Answer(answer.getAnswerId(),answer.getQuestionId(),
												answer.getPersonId(), answer.getStatusId(), answer.getTagId(), 
												answer.getAnswerDOB(), answer.getAnswer()));
			}
			else
			{
				logger.info("Unauthorize attempt request to change answer through person Id : "+ user.getPersonId());
				throw new UnAuthorizeRequestException("Forbidden");
			}
		} catch (EntityNotFoundException e)
		{
				logger.error("unable to find answer (as) answer Id: "+answerRequest.getAnswerId()+
							",   Stack Trace : " + e.getStackTrace());
				return new ResponseEntity<>(new ErrorMessage(new Date(), 404, e.getMessage(), "Not Found", ""),
							new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		catch (Exception e)
		{
			logger.error("Unable to update answer !(as) for answer Id : "+answerRequest.getAnswerId()+
						",   Stack Trace : " + e.getStackTrace());
			return new ResponseEntity<>(new ErrorMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
