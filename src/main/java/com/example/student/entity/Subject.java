package com.example.student.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("subject_name")
    private String subjectName;

    @JsonProperty("subject_code")
    private String subjectCode;

    @JsonProperty("marks_obtained")
    private int marksObtained;
}
