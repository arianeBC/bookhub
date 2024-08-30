package com.example.bookhub.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Long> savedBook(
            @Valid @RequestBody BookRequest request,
            Authentication connectedUser) {
        return ResponseEntity.ok(bookService.save(request, connectedUser));
    }

    @GetMapping("{book-id}")
    public ResponseEntity<BookResponse> getBook(
            @PathVariable("book-id") long bookId
    ) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }
}
