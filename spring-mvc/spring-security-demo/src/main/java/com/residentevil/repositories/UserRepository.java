package com.residentevil.repositories;

import com.residentevil.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findOneByUsername(String username);

    @Query(value = "SELECT u FROM User AS u")
    List<User> findAll();
}
