package com.example.lab6.repository;

import com.example.lab6.pojo.Wizard;
import com.example.lab6.pojo.Wizards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WizardService {

    @Autowired
    private WizardRepository wizardRepository;
    public WizardService(WizardRepository wizardRepository){
        this.wizardRepository = wizardRepository;
    }
    public List<Wizard> allWizard(){
        return wizardRepository.findAll();
    }
    public Wizard findWizard(String name){
        return wizardRepository.findWizardByName(name);
    }
    public Wizard findWizardById(String id){
        return wizardRepository.findWizardById(id);
    }
    public Wizard createWizard(Wizard wizard){
        return wizardRepository.save(wizard);
    }
    public Wizard updateWizard(Wizard wizard){
        return wizardRepository.save(wizard);
    }
    public boolean deleteWizard(Wizard wizard){
        try{
            wizardRepository.delete(wizard);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
