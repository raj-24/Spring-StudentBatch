package com.example.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.student.entity.Faculty;
import com.example.student.repository.FacultyRepository;

@Service
public class FacultyService {
	private final FacultyRepository facultyRepository;

	public FacultyService(FacultyRepository facultyRepository) {
		this.facultyRepository = facultyRepository;
	}

	public List<Faculty> getAllFaculty() {
		return facultyRepository.findAll();
	}

	public void addStudentService(Faculty faculty) {
		facultyRepository.save(faculty);

	}

	public Optional<Faculty> getfacultyById(Long id) {
		return facultyRepository.findById(id);
	}

	public List<Faculty> searchFaculty(String query) {
		return facultyRepository.findByNameContainingIgnoreCase(query);
	}


}
