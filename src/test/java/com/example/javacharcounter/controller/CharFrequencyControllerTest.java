package com.example.javacharcounter.controller;

import com.example.javacharcounter.dto.CharFrequencyRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CharFrequencyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenTextIsTooShort_thenReturnsValidationError() throws Exception {
        String requestBody = "{}";

        mockMvc.perform(post("/api/charFrequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", containsString("Текст не может быть пустым.")));
    }

    @Test
    void whenTextIsTooLong_thenReturnsValidationError() throws Exception {
        CharFrequencyRequest request = new CharFrequencyRequest();
        request.setText(String.join("", Collections.nCopies(501, "a")));
        String requestBody = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(post("/api/charFrequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error",
                        containsString("Длина строки должна быть от 1 до 500 символов.")));
    }

    @Test
    void whenTextIsValid_thenReturnsFrequencies() throws Exception {
        CharFrequencyRequest request = new CharFrequencyRequest();
        request.setText("aaaaabcccc");
        String requestBody = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(post("/api/charFrequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].character", is("a")))
                .andExpect(jsonPath("$[0].count", is(5)))
                .andExpect(jsonPath("$[1].character", is("c")))
                .andExpect(jsonPath("$[1].count", is(4)))
                .andExpect(jsonPath("$[2].character", is("b")))
                .andExpect(jsonPath("$[2].count", is(1)));
    }

    @Test
    void whenTextContainsSpaces_thenSpacesAreNotCounted() throws Exception {
        CharFrequencyRequest request = new CharFrequencyRequest();
        request.setText("a b c");
        String requestBody = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(post("/api/charFrequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].character", is("a")))
                .andExpect(jsonPath("$[0].count", is(1)))
                .andExpect(jsonPath("$[1].character", is("b")))
                .andExpect(jsonPath("$[1].count", is(1)))
                .andExpect(jsonPath("$[2].character", is("c")))
                .andExpect(jsonPath("$[2].count", is(1)))
                .andExpect(jsonPath("$", not(hasItem(hasEntry("character", " ")))));
    }

}