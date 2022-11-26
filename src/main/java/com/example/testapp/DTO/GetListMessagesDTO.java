package com.example.testapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetListMessagesDTO {
    private String name;
    private int count;

}
