package com.ReactCrud.demo.service;

import com.ReactCrud.demo.exception.StudentAlreadyExistsException;
import com.ReactCrud.demo.exception.StudentNotFoundException;
import com.ReactCrud.demo.model.Student;
import com.ReactCrud.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class StudentService implements IStudentService{

    private  final StudentRepository studentRepository;


    @Override
    public List<Student> getStudents() {


        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if(studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail()+"already exists!");
        }

        return studentRepository.save(student);
    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st->{
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return  studentRepository.save(st);
        }).orElseThrow(()->new StudentNotFoundException(" Sorry ,This student could not be found."));
    }

    @Override
    public Student getStudentsById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("No student found with this id" + id));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)){
            throw  new StudentNotFoundException("Sorry student not found.");
        }
        studentRepository.deleteById(id);
        }

    }

