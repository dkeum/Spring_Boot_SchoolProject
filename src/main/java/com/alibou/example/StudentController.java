package com.alibou.example;

import org.springframework.web.bind.annotation.RestController;
import java.util.List; // Importing java.util.List instead of org.hibernate.mapping.List

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class StudentController {

    private final StudentRespository repository;

    public StudentController(StudentRespository repository){
        this.repository = repository; 
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "sayHello";
    }

    @PostMapping("/students")
    public StudentResponseDTO post(@RequestBody StudentDTO studentDTO) {
        var student = toStudent(studentDTO);
        var savedStudent = repository.save(student);
        return toStudentResponseDTO(savedStudent);
    }

    private Student toStudent(StudentDTO dto) {
        var student = new Student(); 
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        var school = new School();
        school.setId(dto.schoolId());
        student.setSchool(school);

        return student;
    }

    private StudentResponseDTO toStudentResponseDTO(Student student){
        return new StudentResponseDTO(
            student.getFirstName(),
            student.getLastName(), 
            student.getEmail());
    }


    @GetMapping("/students")
    public List<Student> findAllStudents() { // Using java.util.List<Student> instead of org.hibernate.mapping.List
        return repository.findAll();
    }

    @GetMapping("/students/{student-id}")
    public Student findStudentById(@PathVariable("student-id") Integer Id) {
        return repository.findById(Id).orElse(new Student());
    }

    @GetMapping("/students/search/{student-name}")
    public List<Student> findStudentByFirstName(@PathVariable("student-name") String name) {
        return repository.findAllByfirstNameContaining(name);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("student-id") Integer Id) {
        repository.deleteById(Id);
    }
}
