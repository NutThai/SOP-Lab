package com.example.lab5.Controller;

import java.io.Serializable;
import java.util.ArrayList;

public class Word implements Serializable {
    public ArrayList<String> goodWord;
    public ArrayList<String> badWord;

    public Word() {
        goodWord = new ArrayList<String>();
        badWord = new ArrayList<String>();
        goodWord.add("happy");goodWord.add("enjoy");goodWord.add("life");
        badWord.add("fuck");badWord.add("olo");badWord.add("kuy");
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
