package com.example.lab3.Controller;

public class Customer {
    private String ID;
    private String name;
    private boolean sex;
    private int age;
    public Customer(){
        this ("",null,"Female",0);
    }
    public Customer(String ID, String n, String s, int a){
        setID(ID);
        setName(n);
        setSex(s);
        setAge(a);
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
    public boolean isSex() {
        return sex;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex.toLowerCase().equals("female") ?  true : false;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age < 0 ? 0 : age;
    }


}
