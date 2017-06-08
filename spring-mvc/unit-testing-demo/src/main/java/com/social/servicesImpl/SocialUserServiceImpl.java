package com.social.servicesImpl;

import com.social.entities.SocialUser;
import com.social.repositories.SocialUserRepository;
import com.social.services.RoleService;
import com.social.services.SocialUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

@Service
public class SocialUserServiceImpl implements SocialUserService {

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public void registerOrLogin(User facebookUser) {
        String email = facebookUser.getEmail();
        SocialUser socialUser = this.socialUserRepository.findOneByUsername(email);
        if (socialUser == null) {
            socialUser = registerUser(email);
        }

        loginUser(socialUser);
    }

    private SocialUser registerUser(String email) {
        SocialUser user = new SocialUser();
        user.setUsername(email);
        user.setProvider("FACEBOOK");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addRole(this.roleService.getDefaultRole());
        this.socialUserRepository.save(user);
        return user;
    }

    private void loginUser(SocialUser socialUser) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(socialUser, null, socialUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
