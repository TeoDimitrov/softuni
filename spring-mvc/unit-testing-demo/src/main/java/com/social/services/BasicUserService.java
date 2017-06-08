package com.social.services;

import com.social.models.bindingModels.RegistrationModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface BasicUserService extends UserDetailsService {

    void register(RegistrationModel registrationModel);
}