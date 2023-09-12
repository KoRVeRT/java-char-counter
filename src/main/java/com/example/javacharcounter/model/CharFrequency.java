package com.example.javacharcounter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharFrequency {
    private char character;
    private int count;
}