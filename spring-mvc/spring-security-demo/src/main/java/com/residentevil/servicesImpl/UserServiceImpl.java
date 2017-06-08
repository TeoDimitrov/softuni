package com.residentevil.servicesImpl;

import com.residentevil.errors.Errors;
import com.residentevil.entities.User;
import com.residentevil.models.bindingModels.RegistrationModel;
import com.residentevil.models.viewModels.UserViewModel;
import com.residentevil.repositories.UserRepository;
import com.residentevil.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void register(RegistrationModel registrationModel) {
        User user = this.modelMapper.map(registrationModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        this.userRepository.save(user);
    }

    @Override
    public List<UserViewModel> findAll() {
        List<UserViewModel> userViewModels = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        for (User user : users) {
            UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
            userViewModels.add(userViewModel);
        }

        return userViewModels;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }
}
