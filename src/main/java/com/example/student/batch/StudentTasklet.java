package com.example.student.batch;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StudentTasklet implements Tasklet {
	private static final Logger logger = Logger.getLogger(StudentTasklet.class.getName());
	private final StudentRepository studentRepository;

    StudentTasklet(StudentRepository studentRepository) {
    	logger.info("StudentTasklet loadeded...");
        this.studentRepository = studentRepository;
    }
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    	logger.info("executiton of Main Logic Started...");
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("Student_Data.txt");

        // Read JSON file
        String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

        // Deserialize as a list of students
        List<Student> students = objectMapper.readValue(content, new TypeReference<List<Student>>() {});

        students.forEach(
        		student -> {
        		System.out.println("Processed student: " + student.getName());
                studentRepository.save(student);
                System.out.println("Student data saved successfully.");
        		});
     	logger.info("executiton of Main Logic Ended...");
        return RepeatStatus.FINISHED;
    }
}
