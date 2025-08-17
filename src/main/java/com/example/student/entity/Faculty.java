package com.example.student.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "FACULTY")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FACULTY_ID")
    private Long facultyId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "EMAIL")
    private String email;

}
