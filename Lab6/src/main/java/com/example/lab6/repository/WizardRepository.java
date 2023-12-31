package com.example.lab6.repository;

import com.example.lab6.pojo.Wizard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardRepository extends MongoRepository<Wizard, String> {
    @Query(value = "{name: '?0'}")
    public Wizard findWizardByName(String name);

    @Query(value = "{_id: '?0'}")
    public Wizard findWizardById(String id);
}
