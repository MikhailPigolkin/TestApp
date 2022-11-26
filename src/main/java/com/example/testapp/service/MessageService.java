package com.example.testapp.service;


import com.example.testapp.DTO.CreateMessageDTO;
import com.example.testapp.DTO.GetListMessagesDTO;
import com.example.testapp.entity.Message;
import com.example.testapp.entity.User;
import com.example.testapp.repository.MessageRepository;
import com.example.testapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MessageService {

    public static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;

    // запись сообщения
    public ResponseEntity<Message> createMessage(CreateMessageDTO createMessageDTO) {
        try {
            Message message = new Message();
            User user = userService.getUserByName(createMessageDTO.getName());
            message.setUser(user);
            message.setMessage(createMessageDTO.getMessage());
            return new ResponseEntity<>(messageRepository.save(message), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Сообщение не сохранено! {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // вывод списка сообщений
    public ResponseEntity<List<Message>> getMessages(GetListMessagesDTO getListMessagesDTO) {
        User user = userService.getUserByName(getListMessagesDTO.getName());
        List<Message> messages = messageRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<Message> copy;
        if (messages.size() >= getListMessagesDTO.getCount()) {
            copy = messages.subList(0, getListMessagesDTO.getCount());
        } else
            copy = new ArrayList<>(messages);
        return new ResponseEntity<>(copy, HttpStatus.OK);
    }

}
