package com.social.services;

import org.springframework.social.facebook.api.User;

public interface SocialUserService {

    void registerOrLogin(User facebookUser);
}
