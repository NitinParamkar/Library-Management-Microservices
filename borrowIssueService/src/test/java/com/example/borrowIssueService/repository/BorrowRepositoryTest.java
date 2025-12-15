package com.example.borrowIssueService.repository;

import com.example.borrowIssueService.entity.Borrow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BorrowRepositoryTest {

    @Autowired
    private BorrowRepository borrowRepository;

    @Test
    public void testSaveAndFindBorrow() {
        // Arrange
        Borrow borrow = new Borrow();
        borrow.setBookId(100L);
        borrow.setStudentId(200L);
        borrow.setIssueDate(LocalDate.now());

        // Act
        Borrow savedBorrow = borrowRepository.save(borrow);

        // Assert
        assertThat(savedBorrow).isNotNull();
        assertThat(savedBorrow.getIssueId()).isNotNull();
        assertThat(savedBorrow.getBookId()).isEqualTo(100L);
    }
}
