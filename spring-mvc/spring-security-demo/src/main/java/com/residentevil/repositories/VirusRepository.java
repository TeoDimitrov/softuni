package com.residentevil.repositories;

import com.residentevil.entities.Virus;
import com.residentevil.models.viewModels.VirusViewModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirusRepository extends CrudRepository<Virus, Long> {

    @Query(value = "SELECT v FROM Virus AS v")
    List<Virus> findAll();
}
