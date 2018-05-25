package com.emmanuelirem.studentassistant.services.data;

import com.emmanuelirem.studentassistant.models.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageService {

    Mono<Message> sendMessage(Message message);

    Flux<Message> getMessageSentToPerson(String personId);

    Flux<Message> getMessagesSentByPerson(String personId);

    Mono<Void> deleteMessageById(String id);

    Mono<Void> deleteAllMessagesByUser(String username);

    Mono<Void> deleteAllMessagesToUser(String username, String recipient);

    Mono<Void> deleteAllReadMessages(String recipient);
}
