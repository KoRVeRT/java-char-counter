package com.example.javacharcounter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CharFrequencyRequest {
    @NotBlank(message = "Текст не может быть пустым.")
    @Size(max = 500, message = "Длина строки должна быть от 1 до 500 символов.")
    private String text;
}