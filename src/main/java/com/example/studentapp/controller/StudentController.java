package com.example.studentapp.controller;

import com.example.studentapp.dto.StudentDTO;
import com.example.studentapp.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	// POST - Add a new student
	@PostMapping
	public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
		StudentDTO savedStudent = studentService.addStudent(studentDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
	}
	
	// GET - Fetch all students
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		List<StudentDTO> students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}
	
	// GET - Fetch student by ID
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
		StudentDTO student = studentService.getStudentById(id);
		return ResponseEntity.ok(student);
	}
	
	// PUT - Update a student
	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> updateStudent(
			@PathVariable Long id,
			@Valid @RequestBody StudentDTO studentDTO) {
		StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
		return ResponseEntity.ok(updatedStudent);
	}
	
	// DELETE - Delete a student
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return ResponseEntity.ok("Student deleted successfully");
	}
}
