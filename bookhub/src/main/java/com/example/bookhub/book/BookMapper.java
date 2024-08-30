package com.example.bookhub.book;

import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book toBook(BookRequest bookRequest) {
        return Book.builder()
                .id(bookRequest.id())
                .title(bookRequest.title())
                .author(bookRequest.author())
                .description(bookRequest.description())
                .archived(false)
                .available(bookRequest.available())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .description(book.getDescription())
                .owner(book.getOwner().getFullName())
//                .coverImage(book.getCoverImage())
                .rating(book.getRate())
                .available(book.isAvailable())
                .archived(book.isArchived())
                .build();
    }
}
