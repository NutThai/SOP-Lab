package com.example.lab6.pojo;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Wizard")
public class Wizard {
    @Id
    private String _id;
    private String name, sex, position, school, house;
    private int money;

    public Wizard() {}

    public Wizard(String _id,String name, String sex, String position, int money, String school, String house) {
        this._id= _id;
        this.name = name;
        this.sex = sex;
        this.position = position;
        this.money = money;
        this.school = school;
        this.house = house;
    }
}
