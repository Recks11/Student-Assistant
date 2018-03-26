package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Message;
import com.emmanuelirem.studentassistant.repository.MessageRepository;
import com.emmanuelirem.studentassistant.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void sendMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public Message getMessageSentToPerson(String personId) {
        return messageRepository.findMessageByRecipient(personId);
    }

    @Override
    public List<Message> getMessagesSentByPerson(String personId) {
        return messageRepository.findMessageBySender(personId);
    }

    @Override
    public void deleteMessageById(long id) {
        messageRepository.deleteMessagesById(id);
    }

    @Override
    public void deleteAllMessagesByUser(String username) {
        messageRepository.deleteMessagesBySender(username);
    }

    @Override
    public void deleteAllMessagesToUser(String username, String recipient) {
        messageRepository.deleteMessagesBySenderAndRecipient(username,recipient);
    }

    @Override
    public void deleteAllReadMessages(String recipient) {
        messageRepository.deleteAllByReadAndRecipient(true, recipient);
    }
}
