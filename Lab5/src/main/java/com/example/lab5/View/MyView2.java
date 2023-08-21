package com.example.lab5.View;

import com.example.lab5.Controller.Word;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route(value = "/index2")
public class MyView2 extends HorizontalLayout {
    private TextField textWord, textSentence;
    private VerticalLayout l1, l2;
    private Button addGood, addBad, addSentence, showSentence;
    private ComboBox goodWords, badWords;
    private TextArea goodSentence, badSentence;

    public MyView2() {
        Word words = new Word();
        l1 = new VerticalLayout();
        l2 = new VerticalLayout();
        textWord = new TextField();
        textSentence = new TextField();
        addGood = new Button("Add Good Word");
        addBad = new Button("Add Bad Word");
        addSentence = new Button("Add Sentence");
        showSentence = new Button("Show Sentence");
        goodWords = new ComboBox();
        badWords = new ComboBox();
        goodSentence = new TextArea();
        badSentence = new TextArea();
        textWord.setLabel("Add Word");
        textSentence.setLabel("Add Sentence");
        goodSentence.setLabel("Good Sentence");
        badSentence.setLabel("Bad Sentence");
        textWord.setWidthFull();
        textSentence.setWidthFull();
        addGood.setWidthFull();
        addBad.setWidthFull();
        goodWords.setWidthFull();
        badWords.setWidthFull();
        goodSentence.setWidthFull();
        badSentence.setWidthFull();
        addSentence.setWidthFull();
        showSentence.setWidthFull();
        goodWords.setItems(words.getGoodWord());
        badWords.setItems(words.getBadWord());
        addGood.addClickListener(event -> {
            ArrayList a;
            a = WebClient.create().get().uri("http://localhost:8080/addGood/"+textWord.getValue()).retrieve().bodyToMono(ArrayList.class).block();
            goodWords.setItems(a);
        });
        addBad.addClickListener(event -> {
            ArrayList a;
            a = WebClient.create().get().uri("http://localhost:8080/addBad/"+textWord.getValue()).retrieve().bodyToMono(ArrayList.class).block();
            badWords.setItems(a);
        });
        addSentence.addClickListener(event -> {

        });
        showSentence.addClickListener(event -> {

        });

        l1.add(textWord, addGood, addBad, goodWords, badWords);
        l2.add(textSentence, addSentence, goodSentence, badSentence, showSentence);
        add(l1, l2);
    }

}
