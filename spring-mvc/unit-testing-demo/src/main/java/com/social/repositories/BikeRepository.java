package com.social.repositories;

import com.social.entities.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    @Query(value = "SELECT b FROM Bike AS b")
    List<Bike> findAll();


    @Query(value = "SELECT b FROM Bike AS b " +
            "WHERE b.gears > 10")
    List<Bike> findBikesWithMoreThan10Gears();
}