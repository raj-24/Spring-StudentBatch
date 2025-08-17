package com.example.student.batch;

import java.util.logging.Logger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class StudentBatchConfig {
	@Value("${student.batch.cron}") // Injecting cron from application.properties
	private String cronExpression;

	private static final Logger logger = Logger.getLogger(StudentBatchConfig.class.getName());
	private final JobLauncher jobLauncher;
	private final Job importStudentJob; // Injected, No Bean Definition Here!

	public StudentBatchConfig(JobLauncher jobLauncher, Job importStudentJob) {
		this.jobLauncher = jobLauncher;
		this.importStudentJob = importStudentJob;
	}

	@Scheduled(cron = "${student.batch.cron}")// Runs every 5 minutes
	public void runBatchJob(){
		logger.info("Starting batch job...");

		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("run.id", System.currentTimeMillis()) // Unique Job Instance
				.toJobParameters();

		try {
			JobExecution execution = jobLauncher.run(importStudentJob, jobParameters);
			logger.info("Batch job finished with status: " + execution.getStatus());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}

	}
}
