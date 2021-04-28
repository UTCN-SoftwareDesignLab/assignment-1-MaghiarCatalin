package com.lab4.demo.book.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String genre;

    @Column
    private int quantity;

    @Column
    private int price;
}
