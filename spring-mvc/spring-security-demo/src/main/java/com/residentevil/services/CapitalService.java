package com.residentevil.services;

import com.residentevil.models.viewModels.CapitalNameViewModel;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface CapitalService {

    Set<CapitalNameViewModel> getAllCapitals();
}
