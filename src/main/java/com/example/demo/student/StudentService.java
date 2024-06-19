package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        System.out.println(student);
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email allready exist");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
//        Optional<Student> studentOptional = studentRepository.findStudentById(studentId);
        boolean isExist = studentRepository.existsById(studentId);
        if(isExist){
            studentRepository.deleteById(studentId);
        }
        else{
            throw new IllegalStateException("Student with id: "+studentId+" does not exist");
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){

        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id: " + studentId + " does not exist"));

        if(name != null && !name.isEmpty() && !Objects.equals(existingStudent.getName(), name)){
            existingStudent.setName(name);
        }

        if(email != null && !email.isEmpty() && !Objects.equals(existingStudent.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            existingStudent.setEmail(email);
        }
    }
}