package com.example.lab6.view;

import com.example.lab6.pojo.Wizard;
import com.example.lab6.pojo.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
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
    private Notification notification;
    private int currentIndex, size;
    private Wizards wizards = new Wizards();

    public void setTextField() {
        if(wizards.getWizards() != null && !wizards.getWizards().isEmpty()){
            Wizard wizard = wizards.getWizards().get(currentIndex);
            notification = new Notification("",5000);
            fullName.setValue(wizard.getName());
            dollar.setValue(wizard.getMoney() + "");
            position.setValue(wizard.getPosition().toUpperCase().charAt(0) + wizard.getPosition().substring(1));
            gender.setValue(wizard.getSex().equals("m") ? "Male" : wizard.getSex().equals("f") ? "Female" : "");
            school.setValue(wizard.getSchool());
            house.setValue(wizard.getHouse());
        }else{
            fullName.setValue("");
            dollar.setValue("");
            position.setValue("");
            gender.setValue("");
            school.setValue("");
            house.setValue("");
        }
    }

    public void loadList() {
        List wiz = WebClient.create().get().uri("http://localhost:8080/wizards").retrieve().bodyToFlux(Wizard.class).collectList().block();
        System.out.println(wiz);
        if(wiz != null && !wiz.isEmpty()){
            wizards.setWizards((ArrayList) wiz);
            size = wizards.getWizards().size();
        }
        else {
            wizards.setWizards(new ArrayList<>());
        }
    }

    public MainWizardView() {
        currentIndex = 0;
        notification = new Notification("",5000);
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

        loadList();
        setTextField();

        left.addClickListener(event -> {
            currentIndex = (((--currentIndex % size) + size) % size);
            setTextField();
        });
        right.addClickListener(event -> {
            currentIndex = (((++currentIndex % size) + size) % size);
            setTextField();
        });
        create.addClickListener(event -> {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("name", fullName.getValue());
            formData.add("sex", gender.getValue());
            formData.add("posi", position.getValue()+"");
            formData.add("money", dollar.getValue());
            formData.add("school", school.getValue()+"");
            formData.add("house", house.getValue()+"");
            Boolean res = WebClient.create().post().uri("http://localhost:8080/addWizard")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(Boolean.class).block();
            if (res) {
                loadList();
                notification.setText("Wizard has been Created");
                notification.open();
            }
        });
        update.addClickListener(event -> {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("id", wizards.getWizards().get(currentIndex).get_id());
            formData.add("name", fullName.getValue());
            formData.add("sex", gender.getValue());
            formData.add("posi", position.getValue()+"");
            formData.add("money", dollar.getValue());
            formData.add("school", school.getValue()+"");
            formData.add("house", house.getValue()+"");
            Boolean res = WebClient.create().post().uri("http://localhost:8080/updateWizard")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(Boolean.class).block();
            if (res) {
                loadList();
                notification.setText("Wizard has been Updated");
                notification.open();
            }
        });
        delete.addClickListener(event -> {
            Boolean res = WebClient.create().post().uri("http://localhost:8080/deleteWizard/{id}",
                    wizards.getWizards().get(currentIndex).get_id()).retrieve().bodyToMono(Boolean.class).block();
            if (res) {
                currentIndex = 0;
                loadList();
                setTextField();
                notification.setText("Wizard has been Removed");
                notification.open();
            }
        });
        add(fullName, gender, position, dollar, school, house, horizontalLayout);
    }
}
