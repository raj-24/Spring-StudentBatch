package com.example.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.entity.Faculty;
import com.example.student.model.ResponseBuilder;
import com.example.student.service.FacultyService;
import com.example.student.util.StudentStatusData;
import com.example.student.util.StudentUtility;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FacultyController {

	private final FacultyService facultyService;

	public FacultyController(FacultyService facultyService) {
		this.facultyService = facultyService;
	}

	@GetMapping("/getallfaculty")
	public List<Faculty> getAllFaculty() {
		return facultyService.getAllFaculty();
	}

	@PostMapping("/addfaculty")
	private ResponseEntity<ResponseBuilder> addStudent(@RequestBody Faculty faculty) {
		String getapireqnum = StudentUtility.getapireqnum();
		facultyService.addStudentService(faculty);
		return new ResponseEntity<ResponseBuilder>(new ResponseBuilder(getapireqnum, StudentUtility.getapirespnum(),
				StudentStatusData.SUCCESS, StudentStatusData.SUCCESSMESSAGE,StudentStatusData.ADDSTUDENTMESSAGE), HttpStatus.OK);
	}
	@GetMapping("/getfacultyById/{id}")
	public Optional<Faculty> getfacultyById(@PathVariable Long id) {
		System.out.println("id:: "+id);
		return facultyService.getfacultyById(id);
	}
	@GetMapping("/facultysearch")
	public List<Faculty> searchFaculty(@RequestParam("query") String query) {
		return facultyService.searchFaculty(query);
	}
}
