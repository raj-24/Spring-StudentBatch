package com.example.student.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.entity.Student;
import com.example.student.model.ResponseBuilder;
import com.example.student.service.StudentManualService;
import com.example.student.util.StudentStatusData;
import com.example.student.util.StudentUtility;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StudentManualController {
	private static final Logger logger = Logger.getLogger(StudentManualController.class.getName());
	private final StudentManualService studentManualService;
	public StudentManualController(StudentManualService studentManualService) {
		logger.info("Student Manual Controller loadeded...");
		this.studentManualService = studentManualService;
	}

	@PostMapping("/addstudent")
	private ResponseEntity<ResponseBuilder> addStudent(@RequestBody Student student) {
		String getapireqnum = StudentUtility.getapireqnum();
		studentManualService.addStudentService(student);
		return new ResponseEntity<ResponseBuilder>(new ResponseBuilder(getapireqnum, StudentUtility.getapirespnum(),
				StudentStatusData.SUCCESS, StudentStatusData.SUCCESSMESSAGE,StudentStatusData.ADDSTUDENTMESSAGE), HttpStatus.OK);
	}
	@GetMapping("/getallstudents")
	public List<Student> getStudents() {
		return studentManualService.getAllStudentsService();
	}
	@GetMapping("/getstudentById/{id}")
	public Optional<Student> getStudentById(@PathVariable Long id) {
		return studentManualService.getStudentById(id);
	}
	@GetMapping("/search")
	public List<Student> searchStudents(@RequestParam("query") String query) {
		return studentManualService.searchStudents(query);
	}

	@GetMapping("/subject-averages")
	public List<Map<String, Object>> getSubjectAverages() {
		return studentManualService.getSubjectAverages();
	}
	@GetMapping("/grade-distribution")
	public List<Map<String, Object>> getGradeDistribution() {
		return studentManualService.getGradeDistribution();
	}
	@GetMapping("/marks-summary")
	public List<Map<String, Object>> getStudentMarksSummary() {
	    return studentManualService.getStudentMarksSummary();
	}
    @PutMapping("/updateStudent/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentManualService.updateStudent(id, student);
    }
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
    	studentManualService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully with id: " + id);
    }

}