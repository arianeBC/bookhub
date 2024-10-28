package com.example.bookhub.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Mock
    private Authentication authentication;

    private Book book;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        book = Book.builder()
                .id(1L)
                .title("Romeo and Juliet")
                .author("William Shakespeare")
                .isbn("978-0743477116")
                .description("...")
                .available(true)
                .build();
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void save_shouldReturnCreated_whenBookIsSaved() throws Exception {
        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content("{\"id\":1,\"title\":\"Romeo and Juliet\",\"author\":\"William Shakespeare\",\"isbn\":\"978-0743477116\",\"description\":\"...\",\"available\":true}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void findById_shouldReturnBookResponse_whenBookExists() throws Exception {
        BookResponse bookResponse = BookResponse.builder()
                .title("Romeo and Juliet")
                .author("William Shakespeare")
                .isbn("978-0743477116")
                .description("...")
                .available(true)
                .archived(false)
                .build();

        when(bookService.findById(1L)).thenReturn(bookResponse);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Romeo and Juliet"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void updateAvailableStatus_shouldReturnOk_whenUserIsOwner() throws Exception {
        when(authentication.getName()).thenReturn("user");
        when(bookService.updateAvailableStatus(1L, authentication)).thenReturn(1L);

        mockMvc.perform(patch("/books/1/available")
                        .contentType("application/json")
                        .content("{\"available\": false}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void borrowBook_shouldReturnOk_whenConditionsMet() throws Exception {
        mockMvc.perform(post("/books/1/borrow"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void returnBorrowedBook_shouldReturnOk_whenConditionsMet() throws Exception {
        mockMvc.perform(patch("/books/1/return"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void archiveBook_shouldReturnOk_whenUserIsOwner() throws Exception {
        mockMvc.perform(patch("/books/1/archive"))
                .andExpect(status().isOk());
    }
}
