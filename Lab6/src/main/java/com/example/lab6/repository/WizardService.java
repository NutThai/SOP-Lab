package com.example.lab6.repository;

import com.example.lab6.pojo.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
public class WizardService {

    @Autowired
    private WizardRepository wizardRepository;

    public WizardService(WizardRepository wizardRepository) {
        this.wizardRepository = wizardRepository;
    }

    @Cacheable(value = "wizard", key = "'wizard'")
    public List<Wizard> allWizard() {
        return wizardRepository.findAll();
    }

    public Wizard findWizard(String name) {
        return wizardRepository.findWizardByName(name);
    }

    public Wizard findWizardById(String id) {
        return wizardRepository.findWizardById(id);
    }

    @CacheEvict(value = "wizard", key = "'wizard'")
    public void createWizard(Wizard wizard) {
        wizardRepository.save(wizard);
    }
    @CachePut(value = "wizard", key = "'wizard'")
    public void updateWizard(Wizard wizard) {
        wizardRepository.save(wizard);
    }
    @CacheEvict(value = "wizard", key = "'wizard'")
    public void deleteWizard(Wizard wizard) {
        try {
            wizardRepository.delete(wizard);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
