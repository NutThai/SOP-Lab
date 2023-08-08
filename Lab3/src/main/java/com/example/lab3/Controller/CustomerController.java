package com.example.lab3.Controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class CustomerController {
    private List<Customer> customers;

    public CustomerController() {
        this.customers = new ArrayList();
        firstAddCustomer("1010","John","Male", 25);
        firstAddCustomer("1018","Peter","Male", 24);
        firstAddCustomer("1019","Sara","Female", 23);
        firstAddCustomer("1110","Rose","Female", 23);
        firstAddCustomer("1001","Emma","Female", 30);

    }

    @RequestMapping(value = "/customers")
    public List<Customer> getCustomers(){
        return customers;
    }
    @RequestMapping(value = "/customerbyid/{id}")
    public Customer getCustomerByID(@PathVariable String id){
        for (Customer i : customers){
            if(i.getID().equals(id)){
                return i;
            }
        }
        return null;
    }
    @RequestMapping(value = "/customerbyname/{n}")
    public Customer getCustomerByName(@PathVariable String n){
        for (Customer i : customers){
            if(i.getName().equals(n)){
                return i;
            }
        }
        return null;
    }
    @RequestMapping(value = "/customerDelByid/{ID}")
    public boolean delCustomerByID(@PathVariable String ID){
        for (Customer i : customers){
            if(i.getID().equals(ID)){
                customers.remove(i);
                return true;
            }
        }
        return false;
    }
    @RequestMapping(value = "/customerDelByname/{n}")
    public boolean delCustomerByName(@PathVariable String n){
        for (Customer i : customers){
            if(i.getName().equals(n)){
                customers.remove(i);
                return true;
            }
        }
        return false;
    }
    @GetMapping(value = "/addCustomer")
    public boolean addCustomer(@RequestParam("id") String ID,@RequestParam("name") String n,@RequestParam("sex") String s,@RequestParam("age") int a){
        this.customers.add(new Customer(ID, n, s, a));
        return true;
    }

    @PostMapping(value = "/addCustomer2")
    public boolean addCustomer2(@RequestParam("id") String ID,@RequestParam("name") String n,@RequestParam("sex") String s,@RequestParam("age") int a){
        this.customers.add(new Customer(ID, n, s, a));
        return true;
    }
    public boolean firstAddCustomer(String ID, String n, String s, int a){
        this.customers.add(new Customer(ID, n, s, a));
        return true;
    }

}
