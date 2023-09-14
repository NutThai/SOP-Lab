package com.example.lab6.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
@Data
public class Wizards implements Serializable {
    private ArrayList<Wizard> wizards;
    public Wizards(){
        wizards = new ArrayList<>();
    }

}
