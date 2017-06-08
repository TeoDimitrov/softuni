package com.wizzmail.repository.implementation;

import com.wizzmail.domain.entity.User;
import com.wizzmail.repository.UserRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

@Stateless
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Query query = this.entityManager.createQuery(
                "SELECT u FROM User AS u " +
                        "WHERE u.username = :username " +
                        "AND u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        User user = null;
        if (!users.isEmpty()) {
            user = users.stream().findFirst().get();
        }

        return user;
    }

    @Override
    public List<User> findUsersByUsernames(String[] usernames) {
        Query query = this.entityManager.createQuery(
                "SELECT u FROM User AS u " +
                        "WHERE u.username IN :usernames");
        query.setParameter("usernames", Arrays.asList(usernames));
        List<User> users = query.getResultList();

        return users;
    }
}
