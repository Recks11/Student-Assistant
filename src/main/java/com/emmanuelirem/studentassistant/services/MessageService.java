package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.Message;

import java.util.List;

public interface MessageService {

    void sendMessage(Message message);

    Message getMessageSentToPerson(String personId);

    List<Message> getMessagesSentByPerson(String personId);

    void deleteMessageById(long id);

    void deleteAllMessagesByUser(String username);

    void deleteAllMessagesToUser(String username, String recipient);

    void deleteAllReadMessages(String recipient);
}
