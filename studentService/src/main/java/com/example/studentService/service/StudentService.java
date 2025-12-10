package com.example.studentService.service;

import com.example.studentService.entity.Student;
import com.example.studentService.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setPhone(studentDetails.getPhone());
        student.setDepartment(studentDetails.getDepartment());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
