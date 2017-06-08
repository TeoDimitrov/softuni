package com.residentevil.servicesImpl;

import com.residentevil.entities.Capital;
import com.residentevil.entities.Virus;
import com.residentevil.models.bindingModels.AddVirusBindingModel;
import com.residentevil.models.bindingModels.EditVirusBindingModel;
import com.residentevil.models.viewModels.VirusViewModel;
import com.residentevil.repositories.CapitalRepository;
import com.residentevil.repositories.VirusRepository;
import com.residentevil.services.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

@Service
public class VirusServiceImpl implements VirusService {

    @Autowired
    private VirusRepository virusRepository;

    @Autowired
    private CapitalRepository capitalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(AddVirusBindingModel addVirusBindingModel) {
        Virus virus = this.modelMapper.map(addVirusBindingModel, Virus.class);
        virus.setDeadly(addVirusBindingModel.getDeadly() == null ? true : false);
        virus.setCurable(addVirusBindingModel.getCurable() == null ? true : false);
        Set<Capital> capitals = this.capitalRepository.findAllByNameIn(addVirusBindingModel.getCapitals());
        virus.setCapitals(capitals);
        this.virusRepository.save(virus);
    }

    @Override
    public void save(EditVirusBindingModel editVirusBindingModel) {
        Virus virus = this.modelMapper.map(editVirusBindingModel, Virus.class);
        virus.setDeadly(editVirusBindingModel.getDeadly() == null ? true : false);
        virus.setCurable(editVirusBindingModel.getCurable() == null ? true : false);
        Set<Capital> capitals = this.capitalRepository.findAllByNameIn(editVirusBindingModel.getCapitals());
        virus.setCapitals(capitals);
        this.virusRepository.save(virus);
    }

    @Override
    public List<VirusViewModel> findAllViruses() {
        List<VirusViewModel> models = new ArrayList<>();
        List<Virus> viruses = this.virusRepository.findAll();
        for (Virus virus : viruses) {
            VirusViewModel model = this.modelMapper.map(virus, VirusViewModel.class);
            models.add(model);
        }

        return models;
    }

    @Override
    public String findAllMapViruses() {
        StringBuilder geoJson = new StringBuilder();
        geoJson.append("{\n" +
                "        \"type\": \"FeatureCollection\",\n" +
                "        \"features\": [");
        List<Virus> viruses = this.virusRepository.findAll();
        StringJoiner stringJoiner = new StringJoiner(",");
        for (Virus virus : viruses) {
            int magnitude = 0;
            switch (virus.getMagnitude()) {
                case LOW:
                    magnitude = 3;
                    break;
                case MEDIUM:
                    magnitude = 5;
                    break;
                case HIGH:
                    magnitude = 7;
                    break;
            }

            for (Capital capital : virus.getCapitals()) {
                String data = String.format("{\n" +
                        "                \"type\": \"Feature\",\n" +
                        "                \"properties\": {\n" +
                        "                    \"mag\" : %d,\n" +
                        "                    \"color\" : \"#f00\"\n" +
                        "                },\n" +
                        "                \"geometry\": {\n" +
                        "                    \"type\": \"Point\",\n" +
                        "                    \"coordinates\": [\n" +
                        "                        %f,\n" +
                        "                        %f\n" +
                        "                    ]\n" +
                        "                }\n" +
                        "            }", magnitude, capital.getLatitude(), capital.getLongitude());
                stringJoiner.add(data);
            }
        }

        geoJson.append(stringJoiner);
        geoJson.append("]\n" +
                "    }");

        return geoJson.toString();
    }

    @Override
    public EditVirusBindingModel findVirusById(long virusId) {
        Virus virus = this.virusRepository.findOne(virusId);
        EditVirusBindingModel editVirusBindingModel = this.modelMapper.map(virus, EditVirusBindingModel.class);
        List<String> capitals = new ArrayList();
        for (Capital capital : virus.getCapitals()) {
            capitals.add(capital.getName());
        }

        editVirusBindingModel.setCapitals(capitals.toArray(new String[0]));

        return editVirusBindingModel;
    }

    @Override
    public void deleteById(long virusId) {
        this.virusRepository.delete(virusId);
    }
}
