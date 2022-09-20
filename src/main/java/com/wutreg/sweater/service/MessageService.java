package com.wutreg.sweater.service;

import com.wutreg.sweater.entity.Message;
import com.wutreg.sweater.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> findByTag(String filter) {
        return messageRepository.findByTag(filter);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
