package com.social.services;

import com.social.models.viewModels.BikeViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BikeService {

    BikeViewModel findById(long id);

    List<BikeViewModel> findAll();

    Page<BikeViewModel> findAll(Pageable pageable);
}
