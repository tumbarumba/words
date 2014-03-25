package com.exubero.words;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class FindWordsTest {
    private static WordDictionary dictionary;

    @BeforeClass
    public static void loadDictionary() throws IOException {
        dictionary = WordDictionary.loadFrom("/testwords.txt");
    }

    @Test
    public void canFindWordsUsingCombinatorialSearch() {
        List<String> words = dictionary.combinatorialSearch("aab");
        assertThat(words, containsInAnyOrder("aa", "ab", "ba"));
    }

    @Test
    public void canFindWordsUsingFullSearch() {
        List<String> words = dictionary.fullSearch("aab");
        assertThat(words, containsInAnyOrder("aa", "ab", "ba"));
    }

}
