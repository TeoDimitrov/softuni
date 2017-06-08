package com.wizzmail.service.implementation;

import com.wizzmail.domain.entity.User;
import com.wizzmail.domain.model.bindingModels.CreateUserModel;
import com.wizzmail.domain.model.bindingModels.LogInModel;
import com.wizzmail.repository.UserRepository;
import com.wizzmail.service.UserService;
import com.wizzmail.utils.parser.interfaces.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void registerUser(CreateUserModel createUserModel) {
        User user = modelParser.convert(createUserModel, User.class);
        this.userRepository.save(user);
    }

    @Override
    public User findLoggedInUser(LogInModel logInModel) {
        User user = this.userRepository.findByUsernameAndPassword(logInModel.getUsername(), logInModel.getPassword());
        return user;
    }

    @Override
    public List<User> findUsersByUsernames(String[] usernames) {
        return this.userRepository.findUsersByUsernames(usernames);
    }
}
