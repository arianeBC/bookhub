package com.example.bookhub.feedback;

import com.example.bookhub.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping("books/{book-id}")
    public ResponseEntity<PageResponse<FeedbackResponse>> getAllFeedbacksByBook(
            @PathVariable("book-id") long bookId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(feedbackService.findAllFeedbacksByBook(bookId, page, size, connectedUser));
    }

    @PostMapping
    public ResponseEntity<Long> saveFeedback(
            @RequestBody FeedbackRequest feedbackRequest,
            Authentication connectedUser) {
        return ResponseEntity.ok(feedbackService.save(feedbackRequest, connectedUser));
    }
}
