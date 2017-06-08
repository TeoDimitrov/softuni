package com.wizzmail.repository.implementation;

import com.wizzmail.domain.entity.Email;
import com.wizzmail.domain.entity.User;
import com.wizzmail.repository.EmailRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class EmailRepositoryImpl implements EmailRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveEmail(Email email) {
        this.entityManager.persist(email);
    }

    @Override
    public Email findById(long id) {
        return this.entityManager.find(Email.class, id);
    }

    @Override
    public List<Email> getInboxEmails(User recipient) {
        Query query = entityManager.createQuery("SELECT e FROM Email AS e " +
                "WHERE e.isSent = true " +
                "AND e.isTrash = false " +
                "AND :user MEMBER OF e.recipients " +
                "ORDER BY e.sentOn DESC");
        query.setParameter("user", recipient);

        return query.getResultList();
    }

    @Override
    public List<Email> getSentEmails(User recipient) {
        Query query = entityManager.createQuery("SELECT e FROM Email AS e " +
                "WHERE e.isSent = true " +
                "AND e.sender = :user " +
                "AND e.isTrash = false " +
                "ORDER BY e.sentOn DESC");
        query.setParameter("user", recipient);

        return query.getResultList();
    }

    @Override
    public List<Email> getDraftEmails(User recipient) {
        Query query = entityManager.createQuery("SELECT e FROM Email AS e " +
                "WHERE e.isSent = false " +
                "AND e.isDraft = true " +
                "AND e.isTrash = false " +
                "AND e.sender = :user " +
                "ORDER BY e.sentOn DESC");
        query.setParameter("user", recipient);

        return query.getResultList();
    }

    @Override
    public List<Email> getTrashEmails(User recipient) {
        Query query = entityManager.createQuery("SELECT e FROM Email AS e " +
                "WHERE e.isTrash = true " +
                "AND :user MEMBER OF e.recipients " +
                "ORDER BY e.sentOn DESC");
        query.setParameter("user", recipient);

        return query.getResultList();
    }

    @Override
    public void deleteEmail(long id) {
        Email email = this.entityManager.find(Email.class, id);
        this.entityManager.remove(email);
    }
}
