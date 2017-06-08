package com.wizzmail.service;

import com.wizzmail.domain.entity.User;
import com.wizzmail.domain.model.bindingModels.CreateUserModel;
import com.wizzmail.domain.model.bindingModels.LogInModel;

import java.util.List;

public interface UserService {

    void registerUser(CreateUserModel createUserModel);

    User findLoggedInUser(LogInModel logInModel);

    List<User> findUsersByUsernames(String[] usernames);
}
