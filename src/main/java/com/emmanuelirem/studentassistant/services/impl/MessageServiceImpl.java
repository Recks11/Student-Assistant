package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Message;
import com.emmanuelirem.studentassistant.repository.MessageRepository;
import com.emmanuelirem.studentassistant.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Mono<Message> sendMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Flux<Message> getMessageSentToPerson(String personId) {
        return messageRepository.findMessageByRecipient(personId);
    }

    @Override
    public Flux<Message> getMessagesSentByPerson(String personId) {
        return messageRepository.findMessageBySender(personId);
    }

    @Override
    public Mono<Void> deleteMessageById(String id) {
        return messageRepository.deleteMessagesById(id);
    }

    @Override
    public Mono<Void> deleteAllMessagesByUser(String username) {
        return messageRepository.deleteMessagesBySender(username);
    }

    @Override
    public Mono<Void> deleteAllMessagesToUser(String username, String recipient) {
        return messageRepository.deleteMessagesBySenderAndRecipient(username,recipient);
    }

    @Override
    public Mono<Void> deleteAllReadMessages(String recipient) {
        return messageRepository.deleteAllByReadAndRecipient(true, recipient);
    }
}
