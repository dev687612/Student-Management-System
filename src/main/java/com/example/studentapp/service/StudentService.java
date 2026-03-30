package com.example.studentapp.service;

import com.example.studentapp.dto.StudentDTO;
import com.example.studentapp.entity.Student;
import com.example.studentapp.exception.ResourceNotFoundException;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	// Add a new student
	public StudentDTO addStudent(StudentDTO studentDTO) {
		Student student = new Student();
		student.setName(studentDTO.getName());
		student.setEmail(studentDTO.getEmail());
		student.setAge(studentDTO.getAge());
		
		Student savedStudent = studentRepository.save(student);
		return convertToDTO(savedStudent);
	}
	
	// Fetch all students
	public List<StudentDTO> getAllStudents() {
		return studentRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	// Fetch student by ID
	public StudentDTO getStudentById(Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
		return convertToDTO(student);
	}
	
	// Update a student
	public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
		
		student.setName(studentDTO.getName());
		student.setEmail(studentDTO.getEmail());
		student.setAge(studentDTO.getAge());
		
		Student updatedStudent = studentRepository.save(student);
		return convertToDTO(updatedStudent);
	}
	
	// Delete a student
	public void deleteStudent(Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
		studentRepository.deleteById(id);
	}
	
	// Helper method to convert Student entity to DTO
	private StudentDTO convertToDTO(Student student) {
		StudentDTO dto = new StudentDTO();
		dto.setId(student.getId());
		dto.setName(student.getName());
		dto.setEmail(student.getEmail());
		dto.setAge(student.getAge());
		return dto;
	}
}
