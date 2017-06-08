package com.social.repositories;

import com.social.entities.SocialUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialUserRepository extends UserRepository<SocialUser> {
}
