package com.example.borrowIssueService.controller;

import com.example.borrowIssueService.entity.Borrow;
import com.example.borrowIssueService.service.BorrowService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BorrowController.class)
public class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowService borrowService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testIssueBook() throws Exception {
        Borrow borrow = new Borrow(null, 1L, 2L, null, null);
        Borrow savedBorrow = new Borrow(1L, 1L, 2L, LocalDate.now(), null);

        when(borrowService.issueBook(any(Borrow.class))).thenReturn(savedBorrow);

        mockMvc.perform(post("/borrow")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(borrow)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.issueId").value(1));
    }

    @Test
    public void testGetAllIssuedBooks() throws Exception {
        Borrow borrow = new Borrow(1L, 1L, 2L, LocalDate.now(), null);
        when(borrowService.getAllIssuedBooks()).thenReturn(Arrays.asList(borrow));

        mockMvc.perform(get("/borrow"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].issueId").value(1));
    }
}
