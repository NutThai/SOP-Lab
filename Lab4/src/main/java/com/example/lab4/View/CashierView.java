package com.example.lab4.View;

import com.example.lab4.Controller.Change;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "/index1")
public class CashierView extends VerticalLayout {

    private TextField amount, b1000,b500,b100,b20,b10,b5,b1;
    private Button cal;
    public CashierView(){

        amount = new TextField();
        b1000= new TextField();
        b500= new TextField();
        b100= new TextField();
        b20= new TextField();
        b10= new TextField();
        b5= new TextField();
        b1= new TextField();
        cal = new Button("คำนวณเงินทอน");
        b1000.setPrefixComponent(new Span ("$1000: "));
        b500.setPrefixComponent(new Span ("$500: "));
        b100.setPrefixComponent(new Span ("$100: "));
        b20.setPrefixComponent(new Span ("$20: "));
        b10.setPrefixComponent(new Span ("$10: "));
        b5.setPrefixComponent(new Span ("$5: "));
        b1.setPrefixComponent(new Span ("$1: "));
        amount.setPrefixComponent(new Span("$"));
        cal.addClickListener(event -> {
           Change change = WebClient.create().get().uri("http://localhost:8080/getChange/"+amount.getValue()).retrieve().bodyToMono(Change.class).block();
           b1000.setValue(change.getB1000()+"");
            b500.setValue(change.getB500()+"");
            b100.setValue(change.getB100()+"");
            b20.setValue(change.getB20()+"");
            b10.setValue(change.getB10()+"");
            b5.setValue(change.getB5()+"");
            b1.setValue(change.getB1()+"");
        });
        add(amount, cal,b1000,b500,b100,b20,b10,b5,b1);
    }
}
