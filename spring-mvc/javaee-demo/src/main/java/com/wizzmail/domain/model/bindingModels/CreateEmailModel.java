package com.wizzmail.domain.model.bindingModels;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateEmailModel {

    @Size(min = 3, max = 50, message = "The subject should be between 3 and 50 symbols.")
    private String subject;

    @Size(max = 300, message = "The message should be up to 50 symbols.")
    private String message;

    @Size(max = 250, message = "The attachment should be up to 250 symbols.")
    private String attachment;

    @NotNull(message = "Invalid Recipients")
    private String recipientsUsername;

    public CreateEmailModel() {
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

    public String getRecipientsUsername() {
        return recipientsUsername;
    }

    public void setRecipientsUsername(String recipientsUsername) {
        this.recipientsUsername = recipientsUsername;
    }
}
