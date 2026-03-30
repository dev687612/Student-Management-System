package com.example.studentapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentappApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testCreateStudent() throws Exception {
		String studentJson = "{\"name\":\"John Doe\",\"email\":\"john@example.com\",\"age\":22}";

		mockMvc.perform(post("/students")
				.contentType(MediaType.APPLICATION_JSON)
				.content(studentJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is("John Doe")))
				.andExpect(jsonPath("$.email", is("john@example.com")));
	}

	@Test
	void testCreateStudentWithInvalidEmail() throws Exception {
		String studentJson = "{\"name\":\"Jane Doe\",\"email\":\"invalid-email\",\"age\":25}";

		mockMvc.perform(post("/students")
				.contentType(MediaType.APPLICATION_JSON)
				.content(studentJson))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors.email", notNullValue()));
	}

	@Test
	void testCreateStudentWithAgeBelow18() throws Exception {
		String studentJson = "{\"name\":\"Young Student\",\"email\":\"young@example.com\",\"age\":15}";

		mockMvc.perform(post("/students")
				.contentType(MediaType.APPLICATION_JSON)
				.content(studentJson))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors.age", notNullValue()));
	}

	@Test
	void testGetAllStudents() throws Exception {
		mockMvc.perform(get("/students"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", isA(java.util.List.class)));
	}

	@Test
	void testGetStudentByIdNotFound() throws Exception {
		mockMvc.perform(get("/students/999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status", is(404)))
				.andExpect(jsonPath("$.message", containsString("not found")));
	}

}
