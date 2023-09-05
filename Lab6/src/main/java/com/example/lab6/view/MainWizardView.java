package com.example.lab6.view;

import com.example.lab6.pojo.Wizard;
import com.example.lab6.pojo.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;


@Route(value = "mainPage.it")
public class MainWizardView extends VerticalLayout {

    private TextField fullName, dollar;
    private RadioButtonGroup<String> gender;
    private ComboBox position, school, house;
    private Button left, right, create, update, delete;
    private HorizontalLayout horizontalLayout;

    private int currentIndex, size;
    private List<Wizard> wizards;
    public void setTextField(){

        wizards = WebClient.create().get().uri("http://localhost:8080/wizards").retrieve().bodyToFlux(Wizard.class).collectList().block();
        size = wizards.size();
        Wizard wizard = wizards.get(currentIndex);
        System.out.println(wizards);
        fullName.setValue(wizard.getName());
        dollar.setValue(wizard.getMoney()+"");
        position.setValue(wizard.getPosition());
        gender.setValue(wizard.getSex().equals("m") ? "Male" : wizard.getSex().equals("f") ? "Female" : "");
        school.setValue(wizard.getSchool());
        house.setValue(wizard.getHouse());
    }
    public MainWizardView() {
        currentIndex = 0;
        fullName = new TextField("", "Fullname");
        dollar = new TextField("Dollars");
        dollar.setPrefixComponent(new Span("$ "));
        gender = new RadioButtonGroup<>("Gender: ", "Male", "Female");
        position = new ComboBox("", "Student", "Teacher");
        position.setPlaceholder("Position");
        school = new ComboBox("", "Hogwarts", "Beauxbatons", "Durmstrang");
        school.setPlaceholder("School");
        house = new ComboBox("", "Gryffindor", "Ravenclaw", "Hufflepuff", "Slyther");
        house.setPlaceholder("House");
        left = new Button("<<");
        right = new Button(">>");
        create = new Button("Create");
        update = new Button("Update");
        delete = new Button("Delete");
        horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(left, create, update, delete, right);
//        int i = (((index % size) + size) % size);


        setTextField();

        left.addClickListener(event -> {
            currentIndex = (((--currentIndex % size) + size) % size);
            setTextField();
        });
        right.addClickListener(event -> {
            currentIndex = (((++currentIndex % size) + size) % size);
            setTextField();
        });

        add(fullName, gender, position, dollar, school, house, horizontalLayout);
    }
}
