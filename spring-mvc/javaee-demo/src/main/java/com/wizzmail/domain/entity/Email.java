package com.wizzmail.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String subject;

    private String message;

    private String attachment;

    @ManyToMany
    @JoinTable(name = "emails_recipients",
            joinColumns = @JoinColumn(name = "email_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    )
    private List<User> recipients;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    private Date sentOn;

    private boolean isSent;

    private boolean isDraft;

    private boolean isTrash;

    private boolean isRead;

    public Email() {
        this.setRecipients(new ArrayList<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<User> recipients) {
        this.recipients = recipients;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }

    public boolean isTrash() {
        return isTrash;
    }

    public void setTrash(boolean trash) {
        isTrash = trash;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}