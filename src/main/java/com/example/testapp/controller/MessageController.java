package com.example.testapp.controller;

import com.example.testapp.DTO.CreateMessageDTO;
import com.example.testapp.DTO.GetListMessagesDTO;
import com.example.testapp.entity.Message;
import com.example.testapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping()
    public ResponseEntity<Message> createMessage(@RequestBody CreateMessageDTO createMessageDTO){
        return messageService.createMessage(createMessageDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<Message>> getMessages(@RequestBody GetListMessagesDTO getListMessagesDTO){
        return messageService.getMessages(getListMessagesDTO);
    }
}
