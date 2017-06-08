package com.wizzmail.repository;

import com.wizzmail.domain.entity.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    User findByUsernameAndPassword(String username, String password);

    List<User> findUsersByUsernames(String[] usernames);
}
