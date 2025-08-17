package com.example.student.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.student.entity.Student;
import com.example.student.entity.Subject;
import com.example.student.repository.StudentRepository;
@Service
public class StudentManualService {

	private static final Logger logger = Logger.getLogger(StudentManualService.class.getName());
	private final StudentRepository studentRepository;
	public StudentManualService(StudentRepository studentRepository) {
		logger.info("Student Manual Service Class loadeded...");
		this.studentRepository = studentRepository;
	}

	public void addStudentService(Student student) {
		logger.info("add Student Service Started...");
		studentRepository.save(student);
		logger.info("Student Saved Successfully...");
	}


	public List<Student> getAllStudentsService() {
		return studentRepository.findAll();
	}

	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	public List<Map<String, Object>> getSubjectAverages() {
		return studentRepository.findAll().stream()
				.flatMap(student -> student.getSubjects().stream())
				.collect(Collectors.groupingBy(
						subject -> subject.getSubjectName(),
						Collectors.averagingDouble(subject -> subject.getMarksObtained())
						))
				.entrySet().stream()
				.map(entry -> {
					Map<String, Object> result = new HashMap<>();
					result.put("subject", entry.getKey());
					result.put("average", entry.getValue());
					return result;
				})
				.collect(Collectors.toList());
	}


	public List<Map<String, Object>> getGradeDistribution() {
		Map<String, Long> distribution = studentRepository.findAll().stream()
				.map(student -> {
					double avg = student.getSubjects().stream()
							.mapToInt(subject -> subject.getMarksObtained())
							.average()
							.orElse(0);
					if (avg >= 90) return "A";
					if (avg >= 80) return "B";
					return "C";
				})
				.collect(Collectors.groupingBy(grade -> grade, Collectors.counting()));

		List<Map<String, Object>> result = new ArrayList<>();
		String[] grades = {"A", "B", "C"};

		for (String grade : grades) {
			Map<String, Object> map = new HashMap<>();
			map.put("grade", grade);
			map.put("count", distribution.getOrDefault(grade, 0L));
			result.add(map);
		}

		return result;
	}

	public List<Student> searchStudents(String query) {
		return studentRepository.findByNameContainingIgnoreCase(query);
	}
	/*
	    public List<Map<String, Object>> getStudentMarksSummary() {
	        List<Student> students = studentRepository.findAll();

	        return students.stream().map(student -> {
	            int totalMarks = student.getSubjects()
	                                    .stream()
	                                    .mapToInt(Subject::getMarksObtained)
	                                    .sum();

	            Map<String, Object> data = new HashMap<>();
	            data.put("name", student.getName());
	            data.put("totalMarks", totalMarks);
	            return data;
	        }).collect(Collectors.toList());
	    }
	}
	 */

	public List<Map<String, Object>> getStudentMarksSummary() {
		List<Student> students = studentRepository.findAll();
		List<Map<String, Object>> summaryList = new ArrayList<>();

		for (Student student : students) {
			int totalMarks = 0;
			if (student.getSubjects() != null) {
				for (Subject subject : student.getSubjects()) {
					totalMarks += subject.getMarksObtained();
				}
			}

			Map<String, Object> studentMap = new HashMap<>();
			studentMap.put("name", student.getName());
			studentMap.put("totalMarks", totalMarks);
			summaryList.add(studentMap);
		}

		return summaryList;
	}
	public Student updateStudent(Long id, Student updatedStudent) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
		if (optionalStudent.isPresent()) {
			// Ensure ID is preserved
			updatedStudent.setStudentId(id);
			return studentRepository.save(updatedStudent);
		} else {
			throw new RuntimeException("Student not found with id: " + id);
		}
	}
	public void deleteStudent(Long id) {
		if (studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
		} else {
			throw new RuntimeException("Student not found with id: " + id);
		}
	}


}