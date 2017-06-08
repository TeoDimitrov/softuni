package com.residentevil.services;

import com.residentevil.models.bindingModels.AddVirusBindingModel;
import com.residentevil.models.bindingModels.EditVirusBindingModel;
import com.residentevil.models.viewModels.VirusViewModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface VirusService {

    void save(AddVirusBindingModel addVirusBindingModel);

    void save(EditVirusBindingModel editVirusBindingModel);

    List<VirusViewModel> findAllViruses();

    String findAllMapViruses();

    EditVirusBindingModel findVirusById(long virusId);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteById(long virusId);
}
