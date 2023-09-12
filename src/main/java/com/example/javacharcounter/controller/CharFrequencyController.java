package com.example.javacharcounter.controller;

import com.example.javacharcounter.dto.CharFrequencyRequest;
import com.example.javacharcounter.model.CharFrequency;
import com.example.javacharcounter.service.CharFrequencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class CharFrequencyController {
    private final CharFrequencyService charFrequencyService;

    @PostMapping("/charFrequency")
    public List<CharFrequency> getCharFrequency(@RequestBody @Valid CharFrequencyRequest request) {
        return charFrequencyService.calculateFrequency(request.getText().replace(" ", ""));
    }
}