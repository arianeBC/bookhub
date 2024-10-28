package com.example.bookhub.book;

import com.example.bookhub.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{book-id}")
    public ResponseEntity<BookResponse> getBook(
            @PathVariable("book-id") long bookId
    ) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> getAllBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllBooks(page, size, connectedUser));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> getAllBooksByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllBooksByOwner(page, size, connectedUser));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> getAllBorrowedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllBorrowedBooks(page, size, connectedUser));
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> getAllReturnedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllReturnedBooks(page, size, connectedUser));
    }

    @GetMapping("/bookmark")
    public ResponseEntity<List<BookResponse>> getBookmarkedBooks(Authentication connectedUser) {
        return ResponseEntity.ok(bookService.findAllBookmarkedBooks(connectedUser));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<BookResponse>> searchDisplayableBooks(
            @RequestParam String keyword,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser) {
        return ResponseEntity.ok(bookService.searchDisplayableBooks(page, size, connectedUser, keyword));
    }

    @PostMapping
    public ResponseEntity<Long> savedBook(
            @Valid @RequestBody BookRequest bookRequest,
            Authentication connectedUser) {
        return ResponseEntity.ok(bookService.save(bookRequest));
    }

    @PostMapping(value = "/{book-id}/cover", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadBookCoverImage(
            @PathVariable("book-id") Long bookId,
            @Parameter()
            @RequestPart MultipartFile sourceFile,
            Authentication connectedUser) {
        bookService.uploadBookCoverImage(sourceFile, connectedUser, bookId);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/{book-id}/borrow")
    public ResponseEntity<Long> borrowBook(
            @PathVariable("book-id") Long bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.borrowBook(bookId, connectedUser));
    }

    @PostMapping("/{book-id}/bookmark")
    public ResponseEntity<Void> saveBookForLater(
            @PathVariable("book-id") Long bookId,
            Authentication connectedUser
    ) {
        bookService.saveBookForLater(bookId, connectedUser);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{book-id}/available")
    public ResponseEntity<Long> updateAvailableStatus(
            @PathVariable("book-id") Long bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.updateAvailableStatus(bookId, connectedUser));
    }

    @PatchMapping("/{book-id}/archive")
    public ResponseEntity<Long> updateArchivedStatus(
            @PathVariable("book-id") Long bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.updateArchivedStatus(bookId, connectedUser));
    }

    @PatchMapping("/{book-id}/return")
    public ResponseEntity<Long> returnBorrowedBook(
            @PathVariable("book-id") Long bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.returnBorrowedBook(bookId, connectedUser));
    }

    @PatchMapping("/{book-id}/return/approve")
    public ResponseEntity<Long> approveBookReturn(
            @PathVariable("book-id") Long bookId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.approveBookReturn(bookId, connectedUser));
    }

    @DeleteMapping("/{book-id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable("book-id") long bookId,
            Authentication connectedUser
    ) {
        bookService.deleteBook(bookId, connectedUser);
        return ResponseEntity.noContent().build();
    }
}
