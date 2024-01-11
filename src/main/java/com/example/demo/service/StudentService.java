package com.example.demo.service;

import com.example.demo.dto.StudentDto;

public interface StudentService {

	public StudentDto saveStudent(StudentDto student);
	
	public StudentDto getStudent(long id);

}
