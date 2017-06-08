package com.residentevildemo.service;

import com.residentevildemo.models.VirusBindingModel;

public interface VirusService {

    void create(VirusBindingModel virusBindingModel);

    String getGeoData();
}
