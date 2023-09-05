package com.example.lab6.pojo;

import lombok.Data;

import java.util.ArrayList;
@Data
public class Wizards {
    private ArrayList<Wizard> wizards;
    public Wizards(){
        wizards = new ArrayList<>();
    }

}
