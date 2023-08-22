package com.example.lab5.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Word implements Serializable {
    public ArrayList<String> goodWord;
    public ArrayList<String> badWord;

    public Word() {
        goodWord = new ArrayList<String>(new ArrayList<String>(Arrays.asList("happy", "enjoy", "life")));
        badWord = new ArrayList<String>(new ArrayList<String>(Arrays.asList("fuck", "olo", "kuy")));
    }

    public ArrayList<String> getGoodWord() {
        return goodWord;
    }

    public void setGoodWord(ArrayList<String> goodWord) {
        this.goodWord = goodWord;
    }

    public ArrayList<String> getBadWord() {
        return badWord;
    }

    public void setBadWord(ArrayList<String> badWord) {
        this.badWord = badWord;
    }


}
