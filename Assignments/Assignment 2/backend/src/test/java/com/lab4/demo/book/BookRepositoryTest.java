package com.lab4.demo.book;

import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.book.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
    }

    @Test
    public void testMock() {
        Book bookSaved = repository.save(Book.builder().title("Title1").author("Author1").genre("Genre1").
                price(50).quantity(5).build());

        assertNotNull(bookSaved);
    }

    @Test
    public void findAll() {
        List<Book> books = TestCreationFactory.listOf(Book.class);
        repository.saveAll(books);
        List<Book> all = repository.findAll();
        assertEquals(books.size(), all.size());
    }
}
