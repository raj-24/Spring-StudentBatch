package com.example.student.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Student {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_SEQ")
    @SequenceGenerator(name = "STUDENT_SEQ", sequenceName = "STUDENT_SEQ", allocationSize = 1)
	private Long studentId;
	private String name;
	private int age;
	private String gender;

	@Embedded
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private List<Subject> subjects;
}
