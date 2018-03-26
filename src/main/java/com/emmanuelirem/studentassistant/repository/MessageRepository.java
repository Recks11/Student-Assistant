package com.emmanuelirem.studentassistant.repository;


import com.emmanuelirem.studentassistant.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>, CrudRepository<Message,Long>{

    Message findMessageByRecipient(String personId);

    List<Message> findMessageBySender(String personId);

    void deleteMessagesById(long id);

    void deleteMessagesBySender(String sender);

    void deleteMessagesBySenderAndRecipient(String sender, String recipient);

    void deleteAllByReadAndRecipient(boolean read, String recipient);


}