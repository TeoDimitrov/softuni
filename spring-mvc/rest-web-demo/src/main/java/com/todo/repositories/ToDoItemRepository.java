package com.todo.repositories;

import com.todo.entities.ToDoItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {

    @Query(value = "SELECT t FROM ToDoItem AS t")
    List<ToDoItem> findAll();

    @Query(value = "SELECT t FROM ToDoItem AS t " +
            "WHERE t.category.id = :id")
    List<ToDoItem> findAllByCategoryId(@Param("id") long id);

    @Query(value = "SELECT t FROM ToDoItem AS t " +
            "WHERE t.category.id = :categoryId " +
            "AND t.name LIKE CONCAT('%', :searchWord, '%')")
    List<ToDoItem> findAllByCategoryIdAndName(@Param("searchWord") String searchWord, @Param("categoryId") long categoryId);
}
