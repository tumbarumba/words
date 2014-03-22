package com.exubero.words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class WordDictionary {
    public static WordDictionary loadFrom(String resourceName) throws IOException {
        Trie trie = new Trie();
        InputStream inputStream = WordDictionary.class.getResourceAsStream(resourceName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line = reader.readLine();
            while (line != null) {
                String word = line.trim();
                if (word.length() > 0) {
                    trie.addWord(word);
                }
                line = reader.readLine();
            }
        }
        finally {
            reader.close();
        }

        return new WordDictionary(trie);
    }

    private final Trie words;

    public WordDictionary(Trie words) {
        this.words = words;
    }

    public List<String> words() {
        return words.words();
    }
}
