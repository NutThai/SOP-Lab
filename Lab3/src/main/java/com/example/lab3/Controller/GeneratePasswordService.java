package com.example.lab3.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController

public class GeneratePasswordService {
    @RequestMapping(value = "/{name:[a-z]+}.generate")
    public String generate(@PathVariable String name){
        Random rand = new Random();
        int min = 100000000;
        int max = 999999999;
        int password = (int)(Math.random() * (max - min + 1)) + min;
        return "Hi, "+name+"\nYour new password is "+password+".";
    }
}
