package com.social.servicesImpl;

import com.social.entities.Bike;
import com.social.exception.BikeNotFoundException;
import com.social.models.viewModels.BikeViewModel;
import com.social.repositories.BikeRepository;
import com.social.services.BikeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {

    private ModelMapper modelMapper;

    private BikeRepository bikeRepository;

    @Autowired
    public BikeServiceImpl(ModelMapper modelMapper, BikeRepository bikeRepository) {
        this.modelMapper = modelMapper;
        this.bikeRepository = bikeRepository;
    }

    @Override
    public BikeViewModel findById(long id) {
        Bike bike = this.bikeRepository.findOne(id);
        if (bike == null) {
            throw new BikeNotFoundException();
        }

        BikeViewModel bikeViewModel = this.modelMapper.map(bike, BikeViewModel.class);
        return bikeViewModel;
    }

    @Override
    public List<BikeViewModel> findAll() {
        List<Bike> bikes = this.bikeRepository.findAll();
        List<BikeViewModel> bikeViewModels = new ArrayList();
        for (Bike bike : bikes) {
            BikeViewModel bikeViewModel = this.modelMapper.map(bike, BikeViewModel.class);
            bikeViewModels.add(bikeViewModel);
        }
        return bikeViewModels;
    }

    @Override
    public Page<BikeViewModel> findAll(Pageable pageable) {
        Page<Bike> bikes = this.bikeRepository.findAll(pageable);
        List<BikeViewModel> bikeViewModelList = new ArrayList<>();
        for (Bike bike : bikes) {
            BikeViewModel bikeViewModel = this.modelMapper.map(bike, BikeViewModel.class);
            bikeViewModelList.add(bikeViewModel);
        }

        Page<BikeViewModel> bikeModels = new PageImpl(bikeViewModelList, pageable, bikes.getTotalElements());
        return bikeModels;
    }
}
