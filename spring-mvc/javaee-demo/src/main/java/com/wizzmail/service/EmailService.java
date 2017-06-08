package com.wizzmail.service;

import com.wizzmail.domain.entity.User;
import com.wizzmail.domain.model.bindingModels.CreateEmailModel;
import com.wizzmail.domain.model.viewModels.EmailDetailsViewModel;
import com.wizzmail.domain.model.viewModels.EmailOverviewViewModel;
import com.wizzmail.exception.InvalidRecipientException;

import java.util.List;

public interface EmailService {

    void sendEmail(CreateEmailModel createEmailModel, User sender);

    void sendEmail(long id);

    List<EmailOverviewViewModel> getInboxEmails(User recipient);

    List<EmailOverviewViewModel> getSentEmails(User recipient);

    List<EmailOverviewViewModel> getDraftEmails(User recipient);

    List<EmailOverviewViewModel> getTrashEmails(User recipient);

    EmailDetailsViewModel readReceivedEmail(long id);

    EmailDetailsViewModel readSentEmail(long id);

    void draftEmail(CreateEmailModel createEmailModel, User sender);

    void trashEmail(long id);

    void restoreEmail(long id);

    void deleteEmailPermanently(long id);
}
