package com.example.bookhub.feedback;

import com.example.bookhub.book.Book;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedbackMapper {

    public Feedback toFeedback(FeedbackRequest feedbackRequest, Book book) {
        return Feedback.builder()
                .rating(feedbackRequest.rating())
                .comment(feedbackRequest.comment())
                .book(book)
                .build();
    }

    public FeedbackResponse toFeedbackResponse(Feedback feedback, Long userId) {
        return FeedbackResponse.builder()
                .rating(feedback.getRating())
                .comment(feedback.getComment())
                .connectedUserFeedback(Objects.equals(feedback.getCreatedBy(), userId))
                .build();
    }
}
