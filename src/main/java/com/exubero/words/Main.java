package com.exubero.words;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Loading dictionary...");
        WordDictionary dictionary = loadDictionary();
        System.out.println("Looking up words for " + args[0]);
        List<String> words = dictionary.findWordsUsing(args[0]);
        System.out.println("Found:");
        int count = 0;
        for (String word : words) {
            System.out.println("\t" + ++count + ":\t" + word);
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
