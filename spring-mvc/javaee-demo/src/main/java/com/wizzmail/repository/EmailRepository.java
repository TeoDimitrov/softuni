package com.wizzmail.repository;

import com.wizzmail.domain.entity.Email;
import com.wizzmail.domain.entity.User;

import java.util.List;

public interface EmailRepository {

    void saveEmail(Email email);

    Email findById(long id);

    List<Email> getInboxEmails(User recipient);

    List<Email> getSentEmails(User recipient);

    List<Email> getDraftEmails(User recipient);

    List<Email> getTrashEmails(User recipient);

    void deleteEmail(long id);
}
