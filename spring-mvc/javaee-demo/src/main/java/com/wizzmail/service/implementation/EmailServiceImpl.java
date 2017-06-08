package com.wizzmail.service.implementation;

import com.wizzmail.domain.entity.Email;
import com.wizzmail.domain.entity.User;
import com.wizzmail.domain.model.bindingModels.CreateEmailModel;
import com.wizzmail.domain.model.viewModels.EmailDetailsViewModel;
import com.wizzmail.domain.model.viewModels.EmailOverviewViewModel;
import com.wizzmail.repository.EmailRepository;
import com.wizzmail.service.EmailService;
import com.wizzmail.service.UserService;
import com.wizzmail.utils.parser.interfaces.ModelParser;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class EmailServiceImpl implements EmailService {

    @Inject
    private EmailRepository emailRepository;

    @Inject
    private UserService userService;

    @Inject
    private ModelParser modelParser;

    @Override
    public void sendEmail(CreateEmailModel createEmailModel, User sender) {
        Email email = this.modelParser.convert(createEmailModel, Email.class);
        email.setSender(sender);
        email.setSentOn(new Date());
        email.setSent(true);
        email.setDraft(false);
        String[] usernames = createEmailModel.getRecipientsUsername().split("\\s*@wizmail.bg;\\s*");
        List<User> recipients = this.userService.findUsersByUsernames(usernames);
        if (recipients.size() != usernames.length) {
            throw new EJBException("Invalid Recipients");
        }

        email.setRecipients(recipients);
        this.emailRepository.saveEmail(email);
    }

    @Override
    @Transactional
    public void sendEmail(long id) {
        Email email = this.emailRepository.findById(id);
        email.setSentOn(new Date());
        email.setSent(true);
        email.setDraft(false);
    }

    @Override
    public List<EmailOverviewViewModel> getInboxEmails(User recipient) {
        List<Email> emails = this.emailRepository.getInboxEmails(recipient);
        List<EmailOverviewViewModel> models = new ArrayList<>();
        for (Email email : emails) {
            EmailOverviewViewModel model = this.modelParser.convert(email, EmailOverviewViewModel.class);
            models.add(model);
        }

        return models;
    }

    @Override
    public List<EmailOverviewViewModel> getSentEmails(User recipient) {
        List<Email> emails = this.emailRepository.getSentEmails(recipient);
        List<EmailOverviewViewModel> models = new ArrayList<>();
        for (Email email : emails) {
            EmailOverviewViewModel model = this.modelParser.convert(email, EmailOverviewViewModel.class);
            models.add(model);
        }

        return models;
    }

    @Override
    public List<EmailOverviewViewModel> getDraftEmails(User recipient) {
        List<Email> emails = this.emailRepository.getDraftEmails(recipient);
        List<EmailOverviewViewModel> models = new ArrayList<>();
        for (Email email : emails) {
            EmailOverviewViewModel model = this.modelParser.convert(email, EmailOverviewViewModel.class);
            models.add(model);
        }

        return models;
    }

    @Override
    public List<EmailOverviewViewModel> getTrashEmails(User recipient) {
        List<Email> emails = this.emailRepository.getTrashEmails(recipient);
        List<EmailOverviewViewModel> models = new ArrayList<>();
        for (Email email : emails) {
            EmailOverviewViewModel model = this.modelParser.convert(email, EmailOverviewViewModel.class);
            models.add(model);
        }

        return models;
    }

    @Override
    @Transactional
    public EmailDetailsViewModel readReceivedEmail(long id) {
        Email email = this.emailRepository.findById(id);
        email.setRead(true);
        EmailDetailsViewModel model = this.modelParser.convert(email, EmailDetailsViewModel.class);
        StringBuilder recipients = new StringBuilder();
        for (User user : email.getRecipients()) {
            recipients.append(user.getUsername() + "@wizmail.bg; ");
        }

        model.setRecipientsUsername(recipients.toString());
        model.setSenderUsername(email.getSender().getUsername() + "@wizmail.bg; ");
        return model;
    }

    @Override
    public EmailDetailsViewModel readSentEmail(long id) {
        Email email = this.emailRepository.findById(id);
        EmailDetailsViewModel model = this.modelParser.convert(email, EmailDetailsViewModel.class);
        StringBuilder recipients = new StringBuilder();
        for (User user : email.getRecipients()) {
            recipients.append(user.getUsername() + "@wizmail.bg; ");
        }

        model.setRecipientsUsername(recipients.toString());
        model.setSenderUsername(email.getSender().getUsername() + "@wizmail.bg; ");
        return model;
    }

    @Override
    public void draftEmail(CreateEmailModel createEmailModel, User sender) {
        Email email = this.modelParser.convert(createEmailModel, Email.class);
        email.setSender(sender);
        email.setDraft(true);
        String[] usernames = createEmailModel.getRecipientsUsername().split("\\s*@wizmail.bg;\\s*");
        List<User> recipients = this.userService.findUsersByUsernames(usernames);
        if (recipients.size() != usernames.length) {
            throw new EJBException("Invalid Recipients");
        }

        email.setRecipients(recipients);
        this.emailRepository.saveEmail(email);
    }

    @Override
    @Transactional
    public void trashEmail(long id) {
        Email email = this.emailRepository.findById(id);
        email.setTrash(true);
    }

    @Override
    @Transactional
    public void restoreEmail(long id) {
        Email email = this.emailRepository.findById(id);
        email.setTrash(false);
    }

    @Override
    public void deleteEmailPermanently(long id) {
        this.emailRepository.deleteEmail(id);
    }
}