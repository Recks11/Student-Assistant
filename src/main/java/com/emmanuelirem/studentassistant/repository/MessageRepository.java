package com.emmanuelirem.studentassistant.repository;


import com.emmanuelirem.studentassistant.models.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageRepository extends ReactiveMongoRepository<Message, String>{

    Flux<Message> findMessageByRecipient(String personId);

    Flux<Message> findMessageBySender(String personId);

    Mono<Void> deleteMessagesById(String id);

    Mono<Void> deleteMessagesBySender(String sender);

    Mono<Void> deleteMessagesBySenderAndRecipient(String sender, String recipient);

    Mono<Void> deleteAllByReadAndRecipient(boolean read, String recipient);


}