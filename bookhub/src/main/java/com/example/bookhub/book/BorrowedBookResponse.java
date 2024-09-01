package com.example.bookhub.book;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedBookResponse {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private double rating;
    private boolean returned;
    private boolean returnedApproved;
}
