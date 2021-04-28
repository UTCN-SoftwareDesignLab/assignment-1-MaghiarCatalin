package com.lab4.demo.book.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int quantity;
    private int price;
}
