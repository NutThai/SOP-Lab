package com.example.lab5.Controller;

import org.atmosphere.config.service.Post;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class WordPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    protected Word words;

    public WordPublisher() {
        words = new Word();
    }

    @PostMapping(value = "/addBad/{word}")
    public ArrayList<String> addBadWord(@PathVariable String word) {
        words.getBadWord().add(word);
        return words.getBadWord();
    }

    @PostMapping(value = "/delBad/{word}")
    public ArrayList<String> deleteBadWord(@PathVariable String word) {
        words.getBadWord().remove(word);
        return words.getBadWord();
    }

    @GetMapping(value = "/addGood/{word}")
    public ArrayList<String> addGoodWord(@PathVariable String word) {
        words.getGoodWord().add(word);
        return words.getGoodWord();
    }

    @GetMapping(value = "/delGood/{word}")
    public ArrayList<String> deleteGoodWord(@PathVariable String word) {
        words.getGoodWord().remove(word);
        return words.getGoodWord();
    }

    @PostMapping(value = "/proof/{word}")
    public String proofSentence(@PathVariable String word) {
        boolean good = false;
        boolean bad = false;
        for (String i : words.getGoodWord()) {
            good = word.contains(i);
            if (good) break;
        }
        for (String i : words.getBadWord()) {
            bad = word.contains(i);
            if (bad) break;
        }
        if (good & bad) {
            rabbitTemplate.convertAndSend("Fanout", "", word);

        } else if (good) {
            rabbitTemplate.convertAndSend("Direct", "good", word);

        } else if (bad) {
            rabbitTemplate.convertAndSend("Direct", "bad", word);

        }
        return good & bad ? "Found Bad & Good Word"
                : good ? "Found Good Word"
                : bad ? "Found Bad Word"
                : "Found Nothing";
    }

    @GetMapping(value = "/getSentence")
    public Sentence getSentence() {
        return (Sentence) rabbitTemplate.convertSendAndReceive("Direct", "show", "");
    }
}
