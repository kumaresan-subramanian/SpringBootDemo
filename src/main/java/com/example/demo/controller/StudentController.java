package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Student;

@RestController
@RequestMapping("students")
public class StudentController {
	
	
	@GetMapping("getStudent")
	public ResponseEntity<Student> getStudent() {
		
		return new ResponseEntity<>(new Student(1, "kumar", "jack"), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getStudentList(){
		
		List<Student> students = new ArrayList<>();
		
		students.add(new Student(1, "kumar", "jack"));
		students.add(new Student(2, "anna", "lakshmi"));
		return ResponseEntity.ok().header("customheader", "kumar")
										.body(students);
	}
	
	@GetMapping("{id}/{first-name}/{last-name}")
	public Student getStudentPathVariable(@PathVariable int id,
											@PathVariable("first-name") String firstName,
											@PathVariable("last-name") String lastName) {
		
		return new Student(id, firstName, lastName);
	}
	
	@GetMapping("query")
	public Student getStudentRequestparam(@RequestParam int id,
											@RequestParam String firstName,
											@RequestParam String lastName) {
		
		return new Student(id, firstName, lastName);
	}

	@PostMapping("createStudent")
	@ResponseStatus(HttpStatus.CREATED)
	public Student createStudent(@RequestBody Student student) {
		
		System.out.println(student.getId());
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return student;
	}
	
	@PutMapping("{id}/update")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Student updateStudent(@RequestBody Student student, 
									@PathVariable int id) {
		
		System.out.println(id);
		System.out.println(student.getId());
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		student.setId(id);
		return student;
	}
	
	@DeleteMapping("{id}/delete")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String deleteStudent(@PathVariable int id) {
		
		System.out.println(id);
		return "Student deleted successfully!";
	}
}
