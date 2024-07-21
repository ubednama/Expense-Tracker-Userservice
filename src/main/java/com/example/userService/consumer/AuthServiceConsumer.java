package com.example.userService.consumer;

import com.example.userService.entities.UserInfoDTO;
import com.example.userService.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceConsumer {
    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics ="${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDTO eventData) {
        try {
            userService.createOrUpdateUser(eventData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("AuthServiceConsumer: Exception is thrown while " +
                    "consuming kafka event");
        }
    }
}
