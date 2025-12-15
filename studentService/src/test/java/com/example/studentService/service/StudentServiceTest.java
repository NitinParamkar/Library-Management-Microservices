package com.example.studentService.service;

import com.example.studentService.entity.Student;
import com.example.studentService.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testAddStudent() {
        // Arrange
        Student student = new Student(null, "Alice", "alice@example.com", "1234567890", "CS");
        Student savedStudent = new Student(1L, "Alice", "alice@example.com", "1234567890", "CS");
        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);

        // Act
        Student result = studentService.addStudent(student);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Alice", result.getName());
    }

    @Test
    public void testGetAllStudents() {
        // Arrange
        Student student = new Student(1L, "Alice", "alice@example.com", "1234567890", "CS");
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));

        // Act
        List<Student> result = studentService.getAllStudents();

        // Assert
        assertEquals(1, result.size());
    }
}
