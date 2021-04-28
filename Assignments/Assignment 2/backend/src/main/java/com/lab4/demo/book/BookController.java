package com.lab4.demo.book;

import com.lab4.demo.book.model.dto.BookDTO;
import com.lab4.demo.report.ReportServiceFactory;
import com.lab4.demo.report.ReportType;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(BOOKS)
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<BookDTO> findAll() {
        return bookService.findAll();
    }

    @GetMapping(SPECIFIED_BOOK)
    public List<BookDTO> findSpecific(@PathVariable String specificBook) {
        return bookService.findByNameOrAuthorOrGenre(specificBook);
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO book) {
        return bookService.create(book);
    }

    @PutMapping(ENTITY)
    public BookDTO edit(@PathVariable Integer id, @RequestBody BookDTO book) {
        return bookService.update(id, book);
    }

    @DeleteMapping()
    public void deleteAll() {
        bookService.deleteAll();
    }

    @DeleteMapping(ENTITY)
    public void deleteBook(@PathVariable Integer id) {
        bookService.delete(id);
    }

    @GetMapping(EXPORT_REPORT)
    public void exportReport(@PathVariable ReportType type) {
        reportServiceFactory.getReportService(type).export();
    }

    @PostMapping(ENTITY)
    public ResponseEntity<?> sell(@PathVariable Integer id) {
        if (bookService.sell(id)) return ResponseEntity.ok("Book was sold!");
        else return ResponseEntity
                    .badRequest()
                    .body("Out of stock!");
    }
}
