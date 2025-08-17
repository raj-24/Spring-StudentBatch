package com.example.student.batch;

import java.util.logging.Logger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class StudentJobConfig {
	private static final Logger logger = Logger.getLogger(StudentJobConfig.class.getName());
	@Bean
	public Job importStudentJob(JobBuilderFactory jobBuilderFactory, Step step) {
		logger.info("Starting Job...");
		return jobBuilderFactory.get("importStudentJob")
				.start(step)
				.build();
	}

	@Bean
	public Step step(StepBuilderFactory stepBuilderFactory, StudentTasklet studentTasklet) {
		logger.info("Starting Step...");
		return stepBuilderFactory.get("step")
				.tasklet(studentTasklet)
				.allowStartIfComplete(true)
				.build();
	}
}
