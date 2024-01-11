package com.example.demo.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@SpringBootTest
public class ServiceTest {

	@Mock
	private StudentRepository studentRepositoryMock;

	@InjectMocks
	private StudentService studentService;

	@Test
	public void testSaveStudent() {
		// Create a sample StudentDto
		StudentDto studentDto = new StudentDto();
		studentDto.setId(1L);
		studentDto.setName("John Doe");

		// Create a sample Student
		Student student = new Student();
		BeanUtils.copyProperties(studentDto, student);

		// Mock repository behavior
		when(studentRepositoryMock.save(any(Student.class))).thenReturn(student);

		// Invoke the service method
		StudentDto savedStudentDto = studentService.saveStudent(studentDto);

		// Verify that the repository's save method was called once
		verify(studentRepositoryMock, times(1)).save(any(Student.class));

		// Assert the returned StudentDto fields match the initial input
		assertEquals(studentDto.getId(), savedStudentDto.getId());
		assertEquals(studentDto.getName(), savedStudentDto.getName());
		// Add more assertions for other fields if needed
	}

}
