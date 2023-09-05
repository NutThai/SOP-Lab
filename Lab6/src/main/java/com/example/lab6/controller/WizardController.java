package com.example.lab6.controller;

import com.example.lab6.pojo.Wizard;
import com.example.lab6.pojo.Wizards;
import com.example.lab6.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;

    @GetMapping(value = "/wizards")
    public List<Wizard> getWizard() {
        return wizardService.allWizard();
    }

    @PostMapping(value = "/addWizard")
    public ResponseEntity<Boolean> addWizard(@RequestBody MultiValueMap<String, String> n) {
        Map<String, String> b = n.toSingleValueMap();
        if (wizardService.findWizard(b.get("name")) == null) {
            wizardService.createWizard(new Wizard(null, b.get("name"),
                    b.get("sex").equals("Male") ? "m" : b.get("sex").equals("Female") ? "f" : "",
                    b.get("posi").toLowerCase(), Integer.parseInt(b.get("money")), b.get("school"), b.get("house")));
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);

    }

    @PostMapping(value = "/updateWizard")
    public ResponseEntity<Boolean> updateWizard(@RequestBody MultiValueMap<String, String> n) {
        Map<String, String> b = n.toSingleValueMap();
        wizardService.updateWizard(new Wizard(b.get("id"), b.get("name"),
                b.get("sex").equals("Male") ? "m" : b.get("sex").equals("Female") ? "f" : "",
                b.get("posi").toLowerCase(), Integer.parseInt(b.get("money")), b.get("school"), b.get("house")));
        return ResponseEntity.ok(true);

    }

    @PostMapping(value = "/deleteWizard/{id}")
    public ResponseEntity<Boolean> deleteWizard(@PathVariable String id) {
        System.out.println(id);
        if (wizardService.findWizardById(id) != null) {
            wizardService.deleteWizard(wizardService.findWizardById(id));
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
