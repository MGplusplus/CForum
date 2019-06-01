package com.cforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cforum.model.Answer;

public interface IAnswerRepository extends JpaRepository<Answer, Integer> {

	Iterable<Answer> findByQuestionIdOrderByAnswerIdDesc(int quesId);

}
