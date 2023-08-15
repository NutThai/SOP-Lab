package com.example.lab4.View;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Route(value = "index")
public class MathView extends VerticalLayout {
    private TextField tf1,tf2,ansF;
    private Label l;
    private Button btnPlus,btnMinus, btnMulti, btnDivide, btnMod, btnMax;
    private HorizontalLayout lo;
    public MathView() {
        l = new Label("Operator");
        tf1 = new TextField();
        tf1.setLabel("Number 1");
        tf2 = new TextField();
        tf2.setLabel("Number 2");
        ansF = new TextField();
        ansF.setLabel("Answer");
        ansF.setReadOnly(true);
        lo = new HorizontalLayout();
        btnPlus = new Button("+");
        btnMinus = new Button("-");
        btnMulti = new Button("x");
        btnDivide = new Button("/");
        btnMod = new Button("Mod");
        btnMax = new Button("Max");
        lo.add(btnPlus,btnMinus, btnMulti, btnDivide, btnMod, btnMax);
        btnPlus.addClickListener(event -> {
            double n1 = Double.parseDouble(tf1.getValue());
            double n2 = Double.parseDouble(tf2.getValue());
            String ans = WebClient.create().get().uri("http://localhost:8080/plus/"+n1+"/"+n2).retrieve().bodyToMono(String.class).block();
            ansF.setValue(ans);
        });
        btnMinus.addClickListener(event -> {
            double n1 = Double.parseDouble(tf1.getValue());
            double n2 = Double.parseDouble(tf2.getValue());
            String ans = WebClient.create().get().uri("http://localhost:8080/minus/"+n1+"/"+n2).retrieve().bodyToMono(String.class).block();
            ansF.setValue(ans);
        });
        btnMulti.addClickListener(event -> {
            double n1 = Double.parseDouble(tf1.getValue());
            double n2 = Double.parseDouble(tf2.getValue());
            String ans = WebClient.create().get().uri("http://localhost:8080/multi/"+n1+"/"+n2).retrieve().bodyToMono(String.class).block();
            ansF.setValue(ans);
        });
        btnDivide.addClickListener(event -> {
            double n1 = Double.parseDouble(tf1.getValue());
            double n2 = Double.parseDouble(tf2.getValue());
            String ans = WebClient.create().get().uri("http://localhost:8080/divide/"+n1+"/"+n2).retrieve().bodyToMono(String.class).block();
            ansF.setValue(ans);
        });
        btnMod .addClickListener(event -> {
            double n1 = Double.parseDouble(tf1.getValue());
            double n2 = Double.parseDouble(tf2.getValue());
            String ans = WebClient.create().get().uri("http://localhost:8080/mod/"+n1+"/"+n2).retrieve().bodyToMono(String.class).block();
            ansF.setValue(ans);
        });
        btnMax .addClickListener(event -> {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("n1", tf1.getValue());
            formData.add("n2", tf2.getValue());
            String ans = WebClient.create().post().uri("http://localhost:8080/max").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(String.class).block();
            ansF.setValue(ans);
        });







        add(tf1,tf2,l, lo,ansF);
    }
}
