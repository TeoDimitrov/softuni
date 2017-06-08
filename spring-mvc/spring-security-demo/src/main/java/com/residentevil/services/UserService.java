package com.residentevil.services;

import com.residentevil.models.bindingModels.RegistrationModel;
import com.residentevil.models.viewModels.UserViewModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void register(RegistrationModel registrationModel);

    List<UserViewModel> findAll();
}