package com.example.bookhub.feedback;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedbackService feedbackService;

    @Mock
    private Authentication authentication;

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void saveFeedback_shouldReturnCreated_whenFeedbackIsSaved() throws Exception {
        mockMvc.perform(post("/feedbacks")
                        .contentType("application/json")
                        .content("{\"rating\":4.0,\"comment\":\"Great book!\",\"bookId\":1}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void deleteFeedback_shouldReturnNoContent_whenFeedbackIsDeleted() throws Exception {
        doNothing().when(feedbackService).deleteFeedback(1L, authentication);
        mockMvc.perform(delete("/feedbacks/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void saveFeedback_shouldReturnBadRequest_whenRequestIsInvalid() throws Exception {
        mockMvc.perform(post("/feedbacks")
                        .contentType("application/json")
                        .content("{\"message\":\"\",\"rating\":6}")) // Invalid rating
                .andExpect(status().isBadRequest());
    }
}
