package com.lab4.demo.book;

import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookServiceIntegrationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void findAll() {
        int nrBooks = 10;
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < nrBooks; i++) {
            Book book = Book.builder()
                    .title("Title1 " + i)
                    .author("Author1 " + i)
                    .build();
            books.add(book);
            bookRepository.save(book);
        }

        List<BookDTO> bookDTOList = bookService.findAll();

        for (int i = 0; i < nrBooks; i++) {
            assertEquals(books.get(i).getId(), bookDTOList.get(i).getId());
            assertEquals(books.get(i).getTitle(), bookDTOList.get(i).getTitle());
        }
    }

    @Test
    void createAll() {
        int nrBooks = 10;
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < nrBooks; i++) {
            Book book = Book.builder()
                    .title("Title1 " + i)
                    .author("Author1 " + i)
                    .build();
            books.add(book);
            bookRepository.save(book);
        }

        List<BookDTO> bookDTOList = bookService.findAll();

        for (int i = 0; i < nrBooks; i++) {
            assertEquals(books.get(i).getId(), bookDTOList.get(i).getId());
            assertEquals(books.get(i).getTitle(), bookDTOList.get(i).getTitle());
        }
    }

    @Test
    void updateAll() {
        int nrBooks = 10;
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < nrBooks; i++) {
            Book book = Book.builder()
                    .title("Title1 " + i)
                    .author("Author1 " + i)
                    .build();
            books.add(book);

            book.setAuthor("Some other author" + i);
            bookRepository.save(book);
        }

        List<BookDTO> bookDTOList = bookService.findAll();

        for (int i = 0; i < nrBooks; i++) {
            assertEquals(books.get(i).getId(), bookDTOList.get(i).getId());
            assertEquals(books.get(i).getAuthor(), bookDTOList.get(i).getAuthor());
        }
    }

    @Test
    void deleteAll() {
        bookRepository.deleteAll();

        List<BookDTO> bookDTOList = bookService.findAll();

        assertEquals(0, bookDTOList.size());
    }
}
