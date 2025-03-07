package com.ReactCrud.demo.controller;
import com.ReactCrud.demo.model.Student;
import  com.ReactCrud.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000") //allowing client application to consume the backed
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private  final IStudentService studentService;
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        return  new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);
    }
    @PostMapping
    public  Student addStudent(@RequestBody Student student){
        return  studentService.addStudent(student);
    }
    @PostMapping("/update/{id}")
    public  Student updateStudent(@RequestBody Student student, @PathVariable Long id){
        return  studentService.updateStudent(student , id);

    }
    @DeleteMapping("/delete/{id}")
    public  void  deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
    @GetMapping("/student/{id}")
    public  Student getStudentById(@PathVariable Long id){
        return  studentService.getStudentsById(id);
    }
}
