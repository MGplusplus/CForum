package com.cforum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "t_questions")
public class Question{

	 @Id
	 @GeneratedValue(strategy=GenerationType.SEQUENCE)
	 @Column(name="c_qid")
	 private int questionId;
	 
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "c_person_id", referencedColumnName= "c_person_id" ,nullable = false, insertable = false, updatable = false) 
	 @Fetch(FetchMode.JOIN)
	 @JsonIgnore
	 private Person person;
	 
	 @Column(name = "c_person_id")
	 private Integer personId;
	 
	 //set the default value of status id column to '2'
	 @Column(name="c_status_id") 
	 private int statusId = 2;
	 
	 @Column(name="c_tag_id") 
	 private int tagId;
	 
	 // By default set the date to current timestamp.
	 @Column(name="c_qdob") 
	 @CreationTimestamp
	 private Date QuestionDOB;
	 
	 @Column(name="c_question_desc") 
	 private String questionDesc;

	public Question(Integer personId, int tagId, String questionDesc) {
		this.personId = personId;
		this.tagId = tagId;
		this.questionDesc = questionDesc;
	}

}
