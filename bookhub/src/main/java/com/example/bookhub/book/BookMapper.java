package com.example.bookhub.book;

import com.example.bookhub.file.FileUtils;
import com.example.bookhub.history.BookTransactionHistory;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book toBook(BookRequest bookRequest) {
        return Book.builder()
                .id(bookRequest.id())
                .title(bookRequest.title())
                .author(bookRequest.author())
                .isbn(bookRequest.isbn())
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
                .coverImage(FileUtils.readFileFromLocation(book.getCoverImage()))
                .rating(book.getRate())
                .available(book.isAvailable())
                .archived(book.isArchived())
                .build();
    }

    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory bookTransactionHistory) {
        return BorrowedBookResponse.builder()
                .id(bookTransactionHistory.getBook().getId())
                .title(bookTransactionHistory.getBook().getTitle())
                .author(bookTransactionHistory.getBook().getAuthor())
                .isbn(bookTransactionHistory.getBook().getIsbn())
                .returned(bookTransactionHistory.isReturned())
                .returnedApproved(bookTransactionHistory.isReturnedApproved())
                .build();
    }
}
