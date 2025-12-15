package com.example.studentService.repository;

import com.example.studentService.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveAndFindStudent() {
        // Arrange
        Student student = new Student();
        student.setName("Alice Local");
        student.setEmail("alice.local@example.com");
        student.setPhone("1234567890");
        student.setDepartment("CS");

        // Act
        Student savedStudent = studentRepository.save(student);

        // Assert
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isNotNull();
        assertThat(savedStudent.getName()).isEqualTo("Alice Local");
    }
}
