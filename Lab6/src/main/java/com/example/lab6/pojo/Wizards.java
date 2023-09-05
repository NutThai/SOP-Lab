package com.example.lab6.pojo;

import java.util.ArrayList;

public class Wizards {
    private ArrayList<Wizard> wizards;
    public Wizards(){
        wizards = new ArrayList<>();
    }

    public ArrayList<Wizard> getWizards() {
        return wizards;
    }

    public void setWizards(ArrayList<Wizard> wizards) {
        this.wizards = wizards;
    }
}
