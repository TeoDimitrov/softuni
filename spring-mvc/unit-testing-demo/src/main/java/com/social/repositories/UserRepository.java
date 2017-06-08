package com.social.repositories;

import com.social.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T extends User> extends CrudRepository<T, Long> {

    T findOneByUsername(String username);
}
