package com.example.testapp.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMessageDTO {
    private String name;
    private String message;

    public CreateMessageDTO(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
