package com.emmanuelirem.studentassistant.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String sender;
    private String recipient;
    private String messageHeader;
    private String messageBody;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeSent;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeRead;

    private boolean read;

    public Message() {
    }

    public Message(String sender, String recipient, String messageHeader, String messageBody, Date timeSent, Date timeRead, boolean read) {
        this.sender = sender;
        this.recipient = recipient;
        this.messageHeader = messageHeader;
        this.messageBody = messageBody;
        this.timeSent = timeSent;
        this.timeRead = timeRead;
        this.read = read;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(String messageHeader) {
        this.messageHeader = messageHeader;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    public Date getTimeRead() {
        return timeRead;
    }

    public void setTimeRead(Date timeRead) {
        this.timeRead = timeRead;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", messageHeader='" + messageHeader + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", timeSent=" + timeSent +
                ", timeRead=" + timeRead +
                ", read=" + read +
                '}';
    }
}
