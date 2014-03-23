package com.exubero.words;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class Combinations {
    public static List<String> of(String letters) {
        List<String> wordBuffer = Lists.newArrayList();

        combine(wordBuffer, "", sort(letters));

        return ImmutableList.copyOf(wordBuffer);
    }

    private static String sort(String letters) {
        char[] characters = letters.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }

    private static void combine(List<String> allWords, String word, String letters) {
        if (letters.length() == 0) {
            return;
        }

        String nextWord = word + letters.charAt(0);
        String nextLetters = letters.substring(1);

        allWords.add(nextWord);
        combine(allWords, nextWord, nextLetters);
        combine(allWords, word,     nextLetters);
    }
}
