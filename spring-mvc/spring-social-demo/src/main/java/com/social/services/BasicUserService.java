package com.social.services;

import com.social.models.bindingModels.RegistrationModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface BasicUserService extends UserDetailsService {

    void register(RegistrationModel registrationModel);
}