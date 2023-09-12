package com.example.javacharcounter.service;

import com.example.javacharcounter.model.CharFrequency;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CharFrequencyService {

    public List<CharFrequency> calculateFrequency(String text) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap.entrySet().stream()
                .map(e -> new CharFrequency(e.getKey(),e.getValue()))
                .sorted((a, b) -> b.getCount() - a.getCount())
                .toList();
    }
}