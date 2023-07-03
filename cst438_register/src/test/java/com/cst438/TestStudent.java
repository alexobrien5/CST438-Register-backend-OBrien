package com.cst438;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.cst438.controller.ScheduleController;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import com.cst438.service.GradebookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.context.ContextConfiguration;

/* 
 * Example of using Junit with Mockito for mock objects
 *  the database repositories are mocked with test data.
 *  
 * Mockmvc is used to test a simulated REST call to the RestController
 * 
 * the http response and repository is verified.
 * 
 *   Note: This tests uses Junit 5.
 *  ContextConfiguration identifies the controller class to be tested
 *  addFilters=false turns off security.  (I could not get security to work in test environment.)
 *  WebMvcTest is needed for test environment to create Repository classes.
 */
@ContextConfiguration(classes = { ScheduleController.class })
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
public class TestStudent {

	static final String URL = "http://localhost:8080";
	public static final int TEST_STUDENT_ID = 1;
	public static final String TEST_STUDENT_EMAIL = "test@csumb.edu";
	public static final String TEST_STUDENT_NAME  = "test";
	public static final int TEST_STATUS_CODE = 1;
	public static final String TEST_STATUS = "HOLD";

	@MockBean
	CourseRepository courseRepository;

	@MockBean
	StudentRepository studentRepository;

	@MockBean
	EnrollmentRepository enrollmentRepository;

	@MockBean
	GradebookService gradebookService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void addStudent()  throws Exception {
		
		MockHttpServletResponse response;
		
		Student student = new Student();
		student.setStudent_id(TEST_STUDENT_ID);
		student.setEmail(TEST_STUDENT_EMAIL);
		student.setName(TEST_STUDENT_NAME);
		
		// given  -- stubs for database repositories that return test data
	    given(studentRepository.save(student)).willReturn(student);
		
		// then do an http post request with body of courseDTO as JSON
		response = mvc.perform(
				MockMvcRequestBuilders
			      .post("/addStudent")
			      .content(asJsonString(student))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		// verify that return status = OK (value 200) 
		assertEquals(200, response.getStatus());
		
		// verify that repository save method was called.
		verify(studentRepository).save(any(Student.class));
	}
	
	@Test
	public void changeStatus() throws Exception {
		MockHttpServletResponse response;
		
		Student student = new Student();
		student.setStudent_id(TEST_STUDENT_ID);
		student.setEmail(TEST_STUDENT_EMAIL);
		student.setName(TEST_STUDENT_NAME);
		student.setStatusCode(TEST_STATUS_CODE);
		student.setStatus(TEST_STATUS);
		
		// given  -- stubs for database repositories that return test data
	    given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(student);
	    given(studentRepository.save(student)).willReturn(student);
		
		// then do an http post request with body of courseDTO as JSON
		response = mvc.perform(
				MockMvcRequestBuilders
			      .post("/changeStatus")
			      .content(asJsonString(student))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		// verify that return status = OK (value 200) 
		assertEquals(200, response.getStatus());
		
		// verify that repository save method was called.
		verify(studentRepository).save(any(Student.class));
	}
	
	private static String asJsonString(final Object obj) {
		try {

			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static <T> T  fromJsonString(String str, Class<T> valueType ) {
		try {
			return new ObjectMapper().readValue(str, valueType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}