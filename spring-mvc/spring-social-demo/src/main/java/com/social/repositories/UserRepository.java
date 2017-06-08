package com.social.repositories;

import com.social.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoRepositoryBean
public interface UserRepository<T extends User> extends CrudRepository<T, Long> {

    T findOneByUsername(String username);
}
