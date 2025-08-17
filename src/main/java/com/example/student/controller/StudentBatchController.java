package com.example.student.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBatchController {
	private static final Logger logger = Logger.getLogger(StudentBatchController.class.getName());
	private final JobLauncher jobLauncher;
	private final Job importStudentJob; // Injected, No Bean Definition Here!

	public StudentBatchController(JobLauncher jobLauncher, Job importStudentJob) {
		this.jobLauncher = jobLauncher;
		this.importStudentJob = importStudentJob;
	}
	
	
	@GetMapping("/runBatchJob")
	public  Map<String, Object> runBatchJob() {
		Map<String, Object> response = new HashMap<>();
		try {
			JobParameters jobParameters = new JobParametersBuilder()
					.addLong("run.id", System.currentTimeMillis()) // Unique Job Instance
					.toJobParameters();

			JobExecution jobExecution = jobLauncher.run(importStudentJob, jobParameters);

			response.put("status", jobExecution.getStatus());
			response.put("jobId", jobExecution.getId());
			response.put("message", "Batch job triggered successfully");

			logger.info("Batch job started with ID: {}"+jobExecution.getId());
		} catch (JobExecutionException e) {
			logger.info("Failed to start batch job" + e);
			response.put("status", "FAILED");
			response.put("error", e.getMessage());
		}
		return response;

	}
}
