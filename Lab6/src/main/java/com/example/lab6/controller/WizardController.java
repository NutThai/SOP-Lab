package com.example.lab6.controller;

import com.example.lab6.pojo.Wizard;
import com.example.lab6.pojo.Wizards;
import com.example.lab6.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;

    @GetMapping(value = "wizards")
    public List<Wizard> getWizard() {
        return wizardService.allWizard();
    }

    @PostMapping(value = "addWizard/{name}/{sex}/{posi}/{money}/{school}/{house}")
    public boolean addWizard(@PathVariable String name, @PathVariable String sex,
                             @PathVariable String posi, @PathVariable int money,
                             @PathVariable String school, @PathVariable String house) {
        if (!wizardService.findWizard(name).getName().equals(name)) {
            Wizard wizard = new Wizard(null, name, sex, posi, money, school, house);
            wizardService.createWizard(wizard);
            return true;
        }
        return false;

    }

    @PostMapping(value = "updateWizard/{id}/{name}/{sex}/{posi}/{money}/{school}/{house}")
    public boolean updateWizard(@PathVariable String id, @PathVariable String name, @PathVariable String sex,
                                @PathVariable String posi, @PathVariable int money,
                                @PathVariable String school, @PathVariable String house) {
        try {
            Wizard wizard = wizardService.findWizardById(id);
            wizardService.updateWizard(new Wizard(wizard.get_id(), name, sex, posi, money, school, house));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PostMapping(value = "deleteWizard/{name}")
    public boolean deleteWizard(@PathVariable String name) {
        try {
            Wizard wizard = wizardService.findWizard(name);
            wizardService.deleteWizard(wizard);
        } catch (Exception e) {
            return false;
        }
        return true;

    }
}
