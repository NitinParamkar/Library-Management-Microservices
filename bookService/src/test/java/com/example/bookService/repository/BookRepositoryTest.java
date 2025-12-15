package com.example.bookService.repository;

import com.example.bookService.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveAndFindBook() {
        // Arrange
        Book book = new Book();
        book.setTitle("Test Book Local");
        book.setAuthor("Test Author");
        book.setCategory("Test Category");
        book.setPrice(100.0);

        // Act
        Book savedBook = bookRepository.save(book);

        // Assert
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Test Book Local");
    }

    @Test
    public void testFindById_NotFound() {
        // Act
        Optional<Book> foundBook = bookRepository.findById(99999L); // Use a high ID to avoid conflict

        // Assert
        assertThat(foundBook).isEmpty();
    }
}
