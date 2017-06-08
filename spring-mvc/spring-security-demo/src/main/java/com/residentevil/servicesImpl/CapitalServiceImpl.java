package com.residentevil.servicesImpl;

import com.residentevil.entities.Capital;
import com.residentevil.models.viewModels.CapitalNameViewModel;
import com.residentevil.repositories.CapitalRepository;
import com.residentevil.services.CapitalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CapitalServiceImpl implements CapitalService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CapitalRepository capitalRepository;


    @Override
    public Set<CapitalNameViewModel> getAllCapitals() {
        Set<CapitalNameViewModel> capitalModels = new HashSet<>();
        Set<Capital> capitals = this.capitalRepository.findAllCapitals();
        for (Capital capital : capitals) {
            CapitalNameViewModel viewModel = this.modelMapper.map(capital, CapitalNameViewModel.class);
            capitalModels.add(viewModel);
        }

        return capitalModels;
    }
}
