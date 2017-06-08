package com.todo.repositories;

import com.todo.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query(value = "SELECT c FROM Category AS c")
    List<Category> findAllCategories();

    Category findOneByName(String name);
}
