package com.social.repositories;

import com.social.entities.BasicUser;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicUserRepository extends UserRepository<BasicUser> {
}
