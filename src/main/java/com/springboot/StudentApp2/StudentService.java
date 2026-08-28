package com.springboot.StudentApp2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repo;
	
	public Student saveStudent(Student student) {
		return repo.save(student);
	}
	
	public List<Student> saveAllStudents(List<Student> students){
		return repo.saveAll(students);
	}
	
	public List<Student> getAllStudents(){
		return repo.findAll();
	}
	
	public Student getStudentById(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public void deleteStudent(int id) {
		repo.deleteById(id);
	}
	
	public Student updateStudent(int id, Student updatedStudent) {
		Student existing=repo.findById(id).orElse(null);
		if(existing!=null) {
			existing.setName(updatedStudent.getName());
			existing.setEmail(updatedStudent.getEmail());
			existing.setAge(updatedStudent.getAge());
			return repo.save(existing);
		}
		return null;
	}

}
