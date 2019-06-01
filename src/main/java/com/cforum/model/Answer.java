package com.cforum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="t_answers")
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="c_ans_id")
	private int answerId;
	
	@Column(name="c_qid")
	private int questionId;
	
	@Column(name="c_person_id")
	private int personId;
	
	// Giving default value '2'
	@Column(name="c_status_id")
	private int statusId = 2;
	
	@Column(name="c_tag_id")
	private int tagId;
	
	@Column(name="c_adob")
	@CreationTimestamp					// Add current date by default
	private Date answerDOB;
	
	@Column(name="c_answer")
	private String answer;
	
	public Answer(int questionId, int personId, int tagId, String answer)
	{
		this.questionId = questionId;
		this.personId = personId;
		this.tagId = tagId;
		this.answer = answer;
	}

	
}
