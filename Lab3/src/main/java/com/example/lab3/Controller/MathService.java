package com.example.lab3.Controller;

import org.springframework.web.bind.annotation.*;


@RestController
public class MathService{
    @RequestMapping(value = "/add/{one}/{two}", method = RequestMethod.GET)
    public String add(@PathVariable double one, @PathVariable double two){
        return (one + two+"");
    }
    @RequestMapping(value = "/minus/{one}/{two}")
    public String minus(@PathVariable double one, @PathVariable double two){
        return (one - two+"");
    }
    @RequestMapping(value = "/multiply")
    public String multiply(@RequestParam("num1") double one, @RequestParam("num2")  double two){
        return (one * two+"");
    }
    @RequestMapping(value = "/divide")
    public String divide(@RequestParam("num1") double one, @RequestParam("num2")  double two){
        return (one / two+"");
    }
}