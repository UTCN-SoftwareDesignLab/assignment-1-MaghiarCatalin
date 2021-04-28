package com.lab4.demo.book;

import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private Book findById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::bookToDto).collect(Collectors.toList());
    }

    public List<BookDTO> findByNameOrAuthorOrGenre(String input) {
        return bookRepository.findByNameOrAuthorOrGenre(input).stream().map(bookMapper::bookToDto).collect(Collectors.toList());
    }

    public void deleteAll() {
        bookRepository.deleteAll();
    }

    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public BookDTO update(Integer id, BookDTO bookDTO) {

        Book book = findById(id);

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setGenre(bookDTO.getGenre());
        book.setPrice(bookDTO.getPrice());
        book.setQuantity(bookDTO.getQuantity());

        return bookMapper.bookToDto(bookRepository.save(book));
    }

    public BookDTO create(BookDTO bookDto) {
        return bookMapper.bookToDto(bookRepository.save(bookMapper.fromDTO(bookDto)));
    }

    public boolean sell(Integer id) {

        Book book = findById(id);

        if (book.getQuantity() - 1 > 0)
            book.setQuantity(book.getQuantity() - 1);
        else
            return false;

        bookRepository.save(book);
        return true;
    }

    public List<BookDTO> findOutOfStock() {
        List<BookDTO> result = new ArrayList<>();

        List<BookDTO> books = bookRepository.findAll().stream().map(bookMapper::bookToDto)
                .collect(Collectors.toList());

        for (BookDTO book : books)
            if (book.getQuantity() == 0)
                result.add(book);

        return result;
    }
}
