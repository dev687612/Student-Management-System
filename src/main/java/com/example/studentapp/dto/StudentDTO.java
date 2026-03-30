package com.example.studentapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	
	private Long id;
	
	@NotBlank(message = "Student name is required")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;
	
	@Min(value = 18, message = "Age must be at least 18")
	@Max(value = 100, message = "Age must not exceed 100")
	private Integer age;
}
