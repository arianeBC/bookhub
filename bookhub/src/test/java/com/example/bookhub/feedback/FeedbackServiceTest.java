package com.example.bookhub.feedback;

import com.example.bookhub.book.Book;
import com.example.bookhub.book.BookRepository;
import com.example.bookhub.common.PageResponse;
import com.example.bookhub.exception.OperationNotPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FeedbackServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private FeedbackMapper feedbackMapper;

    @Mock
    private Authentication connectedUser;

    @InjectMocks
    private FeedbackService feedbackService;

    private Book book;
    private FeedbackRequest feedbackRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = Book.builder()
                .id(1L)
                .archived(false)
                .available(true)
                .createdBy("user1")
                .build();
        feedbackRequest = new FeedbackRequest(5.0, "Great book!", 1L);
    }

    @Test
    void save_ShouldReturnFeedbackId_WhenFeedbackIsValid() {
        Feedback feedback = Feedback.builder()
                .id(1L)
                .rating(4.0)
                .comment("Good")
                .book(book)
                .build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(feedbackMapper.toFeedback(feedbackRequest, book)).thenReturn(feedback);
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        Long feedbackId = feedbackService.save(feedbackRequest, connectedUser);

        assertNotNull(feedbackId);
        assertEquals(1L, feedbackId);
        verify(bookRepository).findById(feedbackRequest.bookId());
        verify(feedbackRepository).save(any(Feedback.class));
    }

    @Test
    void save_ShouldThrowEntityNotFoundException_WhenBookNotFound() {
        when(bookRepository.findById(feedbackRequest.bookId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> feedbackService.save(feedbackRequest, connectedUser));
    }

    @Test
    void save_ShouldThrowOperationNotPermittedException_WhenBookIsArchived() {
        book.setArchived(true);
        when(bookRepository.findById(feedbackRequest.bookId())).thenReturn(Optional.of(book));

        assertThrows(OperationNotPermittedException.class, () -> feedbackService.save(feedbackRequest, connectedUser));
    }

    @Test
    void save_ShouldThrowOperationNotPermittedException_WhenBookIsUnavailable() {
        book.setAvailable(false);
        when(bookRepository.findById(feedbackRequest.bookId())).thenReturn(Optional.of(book));

        assertThrows(OperationNotPermittedException.class, () -> feedbackService.save(feedbackRequest, connectedUser));
    }

    @Test
    void save_ShouldThrowOperationNotPermittedException_WhenUserIsBookOwner() {
        when(connectedUser.getName()).thenReturn("user1");
        when(bookRepository.findById(feedbackRequest.bookId())).thenReturn(Optional.of(book));

        assertThrows(OperationNotPermittedException.class, () -> feedbackService.save(feedbackRequest, connectedUser));
    }

    @Test
    void findAllFeedbacksByBook_ShouldReturnPageResponse_WhenFeedbackExists() {
        Feedback feedback = Feedback.builder()
                .id(1L)
                .rating(4.0)
                .comment("Good")
                .book(book)
                .build();
        FeedbackResponse feedbackResponse = new FeedbackResponse(5.0, "Great book!", true);

        when(bookRepository.findById(feedbackRequest.bookId())).thenReturn(Optional.of(book));
        when(feedbackRepository.findAllByBookId(book.getId(), PageRequest.of(0, 10, Sort.by("createdAt").descending())))
                .thenReturn(new PageImpl<>(Collections.singletonList(feedback)));
        when(connectedUser.getName()).thenReturn("user1");
        when(feedbackMapper.toFeedbackResponse(feedback, "user1")).thenReturn(feedbackResponse);

        PageResponse<FeedbackResponse> response = feedbackService.findAllFeedbacksByBook(1L, 0, 10, connectedUser);

        assertNotNull(response);
        assertEquals(1, response.getContent().size());
        assertEquals(5.0, response.getContent().get(0).getRating());
        assertEquals("Great book!", response.getContent().get(0).getComment());
        assertTrue(response.getContent().get(0).isConnectedUserFeedback());
        verify(feedbackRepository).findAllByBookId(book.getId(), PageRequest.of(0, 10, Sort.by("createdAt").descending()));
    }

    @Test
    void findAllFeedbacksByBook_ShouldReturnEmptyPageResponse_WhenNoFeedbackExists() {
        when(bookRepository.findById(feedbackRequest.bookId())).thenReturn(Optional.of(book));
        when(feedbackRepository.findAllByBookId(book.getId(), PageRequest.of(0, 10, Sort.by("createdAt").descending())))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        PageResponse<FeedbackResponse> response = feedbackService.findAllFeedbacksByBook(1L, 0, 10, connectedUser);

        assertNotNull(response);
        assertTrue(response.getContent().isEmpty());
        verify(feedbackRepository).findAllByBookId(book.getId(), PageRequest.of(0, 10, Sort.by("createdAt").descending()));
    }
}
