package com.example.borrowIssueService.service;

import com.example.borrowIssueService.entity.Borrow;
import com.example.borrowIssueService.repository.BorrowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {

    @Mock
    private BorrowRepository borrowRepository;

    @InjectMocks
    private BorrowService borrowService;

    @Test
    public void testIssueBook() {
        // Arrange
        Borrow borrow = new Borrow(null, 1L, 1L, null, null);
        Borrow savedBorrow = new Borrow(1L, 1L, 1L, LocalDate.now(), null);
        when(borrowRepository.save(any(Borrow.class))).thenReturn(savedBorrow);

        // Act
        Borrow result = borrowService.issueBook(borrow);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getIssueId());
        assertNotNull(result.getIssueDate());
    }

    @Test
    public void testGetAllIssuedBooks() {
        // Arrange
        Borrow borrow = new Borrow(1L, 1L, 1L, LocalDate.now(), null);
        when(borrowRepository.findAll()).thenReturn(Arrays.asList(borrow));

        // Act
        List<Borrow> result = borrowService.getAllIssuedBooks();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    public void testReturnBook() {
        // Arrange
        Borrow borrow = new Borrow(1L, 1L, 1L, LocalDate.now(), null);
        when(borrowRepository.findById(1L)).thenReturn(Optional.of(borrow));
        when(borrowRepository.save(any(Borrow.class))).thenReturn(borrow);

        // Act
        Borrow result = borrowService.returnBook(1L);

        // Assert
        assertNotNull(result.getReturnDate());
        verify(borrowRepository, times(1)).save(borrow);
    }
}
