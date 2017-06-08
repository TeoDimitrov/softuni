package com.residentevildemo.serviceImpl;

import com.residentevildemo.repostirory.CapitalRepository;
import com.residentevildemo.service.CapitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapitalServiceImpl implements CapitalService {

    @Autowired
    private CapitalRepository capitalRepository;

    @Override
    public List<String> getCapitals() {
        return this.capitalRepository.getCapitalNames();
    }
}
