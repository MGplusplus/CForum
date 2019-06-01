package com.cforum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cforum.model.Question;


public interface IQuestionRepository extends JpaRepository<Question, Integer>, PagingAndSortingRepository<Question, Integer>{

	Page<Question> findByTagIdOrderByQuestionId(int tagId, Pageable gotoPageDesc);

	Iterable<Question> findByPersonIdOrderByQuestionIdDesc(int personId);

	Question findByQuestionId(int questionId);

	Iterable<Question> findAllByOrderByQuestionIdDesc();

	Iterable<Question> findAllByTagIdOrderByQuestionIdDesc(int tagId);
}
