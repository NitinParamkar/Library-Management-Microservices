package com.example.bookService.service;

import com.example.bookService.entity.Book;
import com.example.bookService.repository.BookRepository;
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
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testAddBook() {
        // Arrange
        Book book = new Book(null, "Title", "Author", "Category", 10.0);
        Book savedBook = new Book(1L, "Title", "Author", "Category", 10.0);
        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        // Act
        Book result = bookService.addBook(book);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Title", result.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testGetAllBooks() {
        // Arrange
        Book book1 = new Book(1L, "Title1", "Author1", "Cat1", 10.0);
        Book book2 = new Book(2L, "Title2", "Author2", "Cat2", 20.0);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        // Act
        List<Book> result = bookService.getAllBooks();

        // Assert
        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookById() {
        // Arrange
        Book book = new Book(1L, "Title", "Author", "Cat", 10.0);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Act
        Optional<Book> result = bookService.getBookById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Title", result.get().getTitle());
    }

    @Test
    public void testDeleteBook() {
        // Act
        bookService.deleteBook(1L);

        // Assert
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
