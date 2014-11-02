package com.exubero.words;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        WordDictionary dictionary = loadDictionary();
        List<String> words = dictionary.fullSearch(args[0]);
        for (String word : words) {
            System.out.println(word);
        }
    }

    private static WordDictionary loadDictionary() {
        try {
            return WordDictionary.loadFrom("/enable1.txt");
        }
        catch (IOException ex) {
            throw new RuntimeException("Unable to load dictionary: " + ex.getMessage());
        }
    }
}
