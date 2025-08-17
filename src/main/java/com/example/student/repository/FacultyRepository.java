package com.example.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long>{
	List<Faculty> findByNameContainingIgnoreCase(String query);
}