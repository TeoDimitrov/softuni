package com.wizzmail.domain.model.viewModels;

import java.util.Date;

public class EmailOverviewViewModel {

    private long id;

    private String subject;

    private String message;

    private String attachment;

    private Date sentOn;

    private boolean isRead;

    private boolean isDraft;

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

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }
}
