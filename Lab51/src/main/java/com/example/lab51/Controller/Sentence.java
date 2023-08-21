package com.example.lab5.Controller;

import java.io.Serializable;
import java.util.ArrayList;

public class Sentence implements Serializable {
    public ArrayList<String> badSentences;
    public ArrayList<String> goodSentences;
    public Sentence(){
        badSentences = new ArrayList<String>();
        goodSentences = new ArrayList<String>();
    }
    public ArrayList<String> getBadSentences() {
        return badSentences;
    }

    public void setBadSentences(ArrayList<String> badSentences) {
        this.badSentences = badSentences;
    }

    public ArrayList<String> getGoodSentences() {
        return goodSentences;
    }

    public void setGoodSentences(ArrayList<String> goodSentences) {
        this.goodSentences = goodSentences;
    }


}
