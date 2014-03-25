package com.exubero.words;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.SortedSet;

import static com.google.common.collect.Collections2.orderedPermutations;

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

    public List<String> combinatorialSearch(String letters) {
        SortedSet<String> foundWords = Sets.newTreeSet();

        for (String combination : Combinations.of(letters.toLowerCase())) {
            for (List<Character> permutation : orderedPermutations(Lists.charactersOf(combination))) {
                String possibleWord = stringFrom(permutation);
                if (words.containsWord(possibleWord)) {
                    foundWords.add(possibleWord);
                }
            }
        }

        return ImmutableList.copyOf(foundWords);
    }

    public List<String> fullSearch(String letters) {
        SortedSet<String> foundWords = Sets.newTreeSet();

        Multiset<Character> letterCounts = letterCountsFrom(letters);

        words.visitWords("", (theWord) -> {
            if (canMakeWord(letterCounts, theWord)) {
                foundWords.add(theWord);
            }
        });

        return ImmutableList.copyOf(foundWords);
    }

    private boolean canMakeWord(Multiset<Character> letterCounts, String theWord) {
        Multiset<Character> wordLetterCounts = letterCountsFrom(theWord);
        for (Character c : wordLetterCounts.elementSet()) {
            if (letterCounts.count(c) < wordLetterCounts.count(c)) {
                return false;
            }
        }
        return true;
    }

    private Multiset<Character> letterCountsFrom(String letters) {
        Multiset<Character> letterCounts = HashMultiset.create();
        for (Character c : letters.toCharArray()) {
            letterCounts.add(c);
        }
        return letterCounts;
    }

    private String stringFrom(List<Character> characters) {
        StringBuilder sb = new StringBuilder();
        for (Character c : characters) {
            sb.append(c);
        }
        return sb.toString();
    }
}
