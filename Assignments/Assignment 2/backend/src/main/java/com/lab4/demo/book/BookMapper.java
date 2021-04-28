package com.lab4.demo.book;

import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO bookToDto(Book book);

    Book fromDTO(BookDTO book);
}
