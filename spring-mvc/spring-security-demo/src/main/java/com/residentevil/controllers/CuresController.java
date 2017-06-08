package com.residentevil.controllers;

import com.residentevil.entities.Magnitude;
import com.residentevil.entities.Mutation;
import com.residentevil.models.bindingModels.AddVirusBindingModel;
import com.residentevil.models.bindingModels.EditVirusBindingModel;
import com.residentevil.models.viewModels.CapitalNameViewModel;
import com.residentevil.models.viewModels.VirusViewModel;
import com.residentevil.services.CapitalService;
import com.residentevil.services.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cures")
public class CuresController {


    @Autowired
    private VirusService virusService;


    @GetMapping("")
    public String getVirusHomePage(Model model) {
        List<VirusViewModel> viruses = this.virusService.findAllViruses();
        model.addAttribute("viruses",viruses);
        return "cures";
    }

    @GetMapping("/delete/{virusId}")
    public String deleteVirus(@PathVariable long virusId) {
        this.virusService.deleteById(virusId);
        return "redirect:/cures";
    }
}
