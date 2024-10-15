package com.example.bookhub.book;

import com.example.bookhub.exception.OperationNotPermittedException;
import com.example.bookhub.history.BookTransactionHistory;
import com.example.bookhub.history.BookTransactionHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookTransactionHistoryRepository bookTransactionHistoryRepository;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private Authentication authentication;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = Book.builder()
                .id(1L)
                .title("Romeo and Juliet")
                .author("William Shakespeare")
                .isbn("978-0743477116")
                .description(
                        "..."
                )
                .archived(false)
                .available(true)
                .createdBy("owner")
                .build();
    }

    @Test
    void save_shouldSaveBook() {
        when(bookMapper.toBook(any(BookRequest.class))).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookRequest bookRequest = new BookRequest(
                1L,
                "Romeo and Juliet",
                "William Shakespeare",
                "978-0743477116",
                "...",
                true
        );

        Long savedBookId = bookService.save(bookRequest);

        assertNotNull(savedBookId);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void findById_shouldReturnBookResponse_whenBookExists() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(bookMapper.toBookResponse(any(Book.class))).thenReturn(new BookResponse());

        BookResponse response = bookService.findById(1L);

        assertNotNull(response);
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void findById_shouldThrowEntityNotFoundException_whenBookDoesNotExist() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookService.findById(1L));
    }

    @Test
    void updateAvailableStatus_shouldToggleAvailableStatus_whenUserIsOwner() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(authentication.getName()).thenReturn("owner");

        Long bookId = bookService.updateAvailableStatus(1L, authentication);

        assertNotNull(bookId);
        assertFalse(book.isAvailable());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void updateAvailableStatus_shouldThrowOperationNotPermittedException_whenUserIsNotOwner() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(authentication.getName()).thenReturn("notOwner");

        book.setCreatedBy("owner");

        assertThrows(OperationNotPermittedException.class, () -> bookService.updateAvailableStatus(1L, authentication));
    }

    @Test
    void borrowBook_shouldBorrowBook_whenConditionsMet() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(authentication.getName()).thenReturn("user");
        when(bookTransactionHistoryRepository.save(any(BookTransactionHistory.class)))
                .thenAnswer(invocation -> {
                    BookTransactionHistory history = invocation.getArgument(0);
                    history.setId(200L);
                    return history;
                });

        Long transactionId = bookService.borrowBook(1L, authentication);

        assertNotNull(transactionId);
        verify(bookTransactionHistoryRepository, times(1)).save(any(BookTransactionHistory.class));
    }

    @Test
    void borrowBook_shouldThrowOperationNotPermittedException_whenBookIsNotAvailable() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(authentication.getName()).thenReturn("user");

        book.setAvailable(false);

        assertThrows(OperationNotPermittedException.class, () -> bookService.borrowBook(1L, authentication));
    }

    @Test
    void returnBorrowedBook_shouldReturnBook_whenConditionsMet() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(authentication.getName()).thenReturn("user");

        BookTransactionHistory history = new BookTransactionHistory();
        when(bookTransactionHistoryRepository.findByBookIdAndUserId(anyLong(), anyString())).thenReturn(Optional.of(history));
        when(bookTransactionHistoryRepository.save(any(BookTransactionHistory.class)))
                .thenAnswer(invocation -> {
                    BookTransactionHistory savedHistory = invocation.getArgument(0);
                    savedHistory.setId(200L);
                    return savedHistory;
                });

        Long transactionId = bookService.returnBorrowedBook(1L, authentication);

        assertNotNull(transactionId);
        assertTrue(history.isReturned());
        verify(bookTransactionHistoryRepository, times(1)).save(history);
    }

    @Test
    void returnBorrowedBook_shouldThrowOperationNotPermittedException_whenUserDidNotBorrowBook() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(authentication.getName()).thenReturn("user");
        when(bookTransactionHistoryRepository.findByBookIdAndUserId(anyLong(), anyString())).thenReturn(Optional.empty());

        assertThrows(OperationNotPermittedException.class, () -> bookService.returnBorrowedBook(1L, authentication));
    }
}
