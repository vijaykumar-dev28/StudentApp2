package com.springboot.StudentApp2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService service;
	
	@PostMapping
	public ResponseEntity<?> saveStudent(@Valid @RequestBody Student student,
	                                      BindingResult result) {
	    if(result.hasErrors()) {
	        // collect all error messages
	        List<String> errors = result.getAllErrors()
	            .stream()
	            .map(e -> e.getDefaultMessage())
	            .collect(Collectors.toList());
	        return ResponseEntity.status(400).body(errors);  // 400 Bad Request
	    }
	    Student saved = service.saveStudent(student);
	    return ResponseEntity.status(201).body(saved);
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<Student>> saveAllStudents(@RequestBody List<Student> students){
		List<Student> savedAll=service.saveAllStudents(students);
		return ResponseEntity.status(201).body(savedAll);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents(){
		return ResponseEntity.ok(service.getAllStudents());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable int id) {
		Student student=service.getStudentById(id);
		if(student != null) {
			return ResponseEntity.ok(student);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable int id){
		service.deleteStudent(id);
		return ResponseEntity.ok("Student deleted successfully !");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable int id,
	                                        @Valid @RequestBody Student updatedStudent,
	                                        BindingResult result) {
	    if(result.hasErrors()) {
	        List<String> errors = result.getAllErrors()
	            .stream()
	            .map(e -> e.getDefaultMessage())
	            .collect(Collectors.toList());
	        return ResponseEntity.status(400).body(errors);  // 400 Bad Request
	    }
	    Student updated = service.updateStudent(id, updatedStudent);
	    if(updated != null) {
	        return ResponseEntity.ok(updated);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
//	@PostMapping
//	public Student saveStudent(@RequestBody Student student) {
//		return service.saveStudent(student);
//	}
//	
//	@PostMapping("/saveAll")
//	public List<Student> saveAllStudents(@RequestBody List<Student> students){
//		return service.saveAllStudents(students);
//	}
//	
//	@GetMapping
//	public List<Student> getAllStudents(){
//		return service.getAllStudents();
//	}
//	
//	@GetMapping("/{id}")
//	public Student saveStudentById(@PathVariable int id) {
//		return service.getStudentById(id);
//	}
//	
//	@DeleteMapping("/{id}")
//	public String deleteStudent(@PathVariable int id) {
//		service.deleteStudent(id);
//		return "Student deleted successfully!";
//	}
	
