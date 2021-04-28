package com.lab4.demo.book;

import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService(bookRepository, bookMapper);
    }

    @Test
    void findAll() {
        List<Book> books = TestCreationFactory.listOf(Book.class);
        when(bookRepository.findAll()).thenReturn(books);

        List<BookDTO> all = bookService.findAll();

        Assertions.assertEquals(books.size(), all.size());
    }

    @Test
    void deleteAll() {
        List<Book> books = TestCreationFactory.listOf(Book.class);
        bookRepository.deleteAll();

        List<BookDTO> all = bookService.findAll();

        Assertions.assertEquals(0, all.size());
    }

    @Test
    void delete() {
        Book book2 = Book.builder()
                .title("Title2")
                .author("Author2")
                .genre("Genre2")
                .build();

        when(bookRepository.save(book2)).thenReturn(book2);

        bookRepository.delete(book2);

        List<BookDTO> all = bookService.findAll();
        Assertions.assertEquals(all.size(), 0);
    }

    @Test
    public void update() {
        Book book2 = Book.builder()
                .title("Title2")
                .author("Author2")
                .genre("Genre2")
                .build();

        BookDTO book = BookDTO.builder()
                .title("Title1")
                .author("Author2")
                .genre("Genre2")
                .quantity(50)
                .build();

        when(bookRepository.findById(book2.getId())).thenReturn(Optional.of(book2));

        bookService.update(book2.getId(), book);
        Assertions.assertEquals(50, book2.getQuantity());
    }

    @Test
    void create() {
        Book book2 = Book.builder()
                .title("Title2")
                .author("Author2")
                .genre("Genre2")
                .build();

        BookDTO book = BookDTO.builder()
                .title("Title1")
                .author("Author2")
                .genre("Genre2")
                .quantity(50)
                .build();

        when(bookMapper.fromDTO(book)).thenReturn(book2);
        when(bookMapper.bookToDto(book2)).thenReturn(book);
        when(bookRepository.save(book2)).thenReturn(book2);

        Assertions.assertEquals(book.getId(), bookService.create(book).getId());
    }

    @Test()
    public void findOutOfStock() {
        List<BookDTO> booksOutOfStock = bookService.findOutOfStock();
        Assertions.assertNotNull(booksOutOfStock);
    }

}
