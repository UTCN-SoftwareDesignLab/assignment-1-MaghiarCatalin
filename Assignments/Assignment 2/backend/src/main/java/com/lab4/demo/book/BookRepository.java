package com.lab4.demo.book;

import com.lab4.demo.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT book FROM Book book WHERE book.title LIKE %:input% OR book.author LIKE %:input% OR book.genre LIKE %:input%")
    List<Book> findByNameOrAuthorOrGenre(@Param("input") String input);
}
