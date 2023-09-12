package com.example.javacharcounter.service;

import com.example.javacharcounter.model.CharFrequency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharFrequencyServiceTest {
    private CharFrequencyService charFrequencyService;

    @BeforeEach
    public void setUp() {
        charFrequencyService = new CharFrequencyService();
    }

    @Test
    void testCalculateFrequency() {
        String text = "ejjwwwwwooo";
        List<CharFrequency> result = charFrequencyService.calculateFrequency(text);

        assertEquals(4, result.size());

        assertEquals('w', result.get(0).getCharacter());
        assertEquals(5, result.get(0).getCount());

        assertEquals('o', result.get(1).getCharacter());
        assertEquals(3, result.get(1).getCount());

        assertEquals('j', result.get(2).getCharacter());
        assertEquals(2, result.get(2).getCount());

        assertEquals('e', result.get(3).getCharacter());
        assertEquals(1, result.get(3).getCount());
    }
}