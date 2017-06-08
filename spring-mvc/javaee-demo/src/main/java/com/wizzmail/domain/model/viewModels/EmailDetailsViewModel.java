package com.wizzmail.domain.model.viewModels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailDetailsViewModel {

    private String senderUsername;

    private String recipientsUsername;

    private String subject;

    private String message;

    private String attachment;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getRecipientsUsername() {
        return recipientsUsername;
    }

    public void setRecipientsUsername(String recipientsUsername) {
        this.recipientsUsername = recipientsUsername;
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
}
