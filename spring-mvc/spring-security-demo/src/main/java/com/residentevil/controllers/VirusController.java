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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/viruses")
public class VirusController {

    @Autowired
    private CapitalService capitalService;

    @Autowired
    private VirusService virusService;

    @ModelAttribute("mutations")
    public Mutation[] getMutations(){
        return Mutation.values();
    }

    @ModelAttribute("magnitudes")
    public Magnitude[] getMagnitudes(){
        return Magnitude.values();
    }

    @ModelAttribute("capitals")
    public Set<CapitalNameViewModel> getCapitalNames(){
        return this.capitalService.getAllCapitals();
    }

    @GetMapping("")
    public String getVirusHomePage(Model model) {
        List<VirusViewModel> viruses = this.virusService.findAllViruses();
        model.addAttribute("viruses",viruses);
        return "viruses";
    }

    @GetMapping("/add")
    public String getAddVirusHomePage(@ModelAttribute AddVirusBindingModel addVirusBindingModel) {
        return "viruses-add";
    }

    @PostMapping("/add")
    public String addVirus(@Valid @ModelAttribute AddVirusBindingModel addVirusBindingModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "viruses-add";
        }

        this.virusService.save(addVirusBindingModel);

        return "redirect:/viruses";
    }

    @GetMapping("/edit/{virusId}")
    public String getEditVirusHomePage(@PathVariable long virusId, Model model) {
        EditVirusBindingModel editVirusBindingModel = this.virusService.findVirusById(virusId);
        model.addAttribute("editVirusBindingModel", editVirusBindingModel);
        return "viruses-edit";
    }

    @PostMapping("/edit/{virusId}")
    public String getEditVirusHomePage(@PathVariable long virusId, @Valid @ModelAttribute EditVirusBindingModel editVirusBindingModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "viruses-edit";
        }

        editVirusBindingModel.setId(virusId);
        this.virusService.save(editVirusBindingModel);
        return "redirect:/viruses";
    }

    @GetMapping("/delete/{virusId}")
    public String deleteVirus(@PathVariable long virusId, Model model) {
        this.virusService.deleteById(virusId);
        return "redirect:/viruses";
    }
}
