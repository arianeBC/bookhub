package com.example.bookhub.book;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String description;
    private String owner;
    private byte[] coverImage;
    private double rating;
    private boolean available;
    private boolean archived;
}
