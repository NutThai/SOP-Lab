package com.example.lab5.Controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SentenceConsumer {
    protected Sentence sentence;
    public SentenceConsumer(){
        sentence = new Sentence();
    }
    @RabbitListener(queues = "BadWordQueue")
    public void addBadSentences(String s){
        sentence.getBadSentences().add(s);
        System.out.println("Bad :"+sentence.getBadSentences());
    }
    @RabbitListener(queues = "GoodWordQueue")
    public void addGoodSentences(String s){
        sentence.getGoodSentences().add(s);
        System.out.println("Good :"+sentence.getGoodSentences());
    }

    @RabbitListener(queues = "GetQueue")
    public Sentence getSentencs(){
        return sentence;
    }

}
