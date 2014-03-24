package com.exubero.words;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class FindWordsTest {
    @Test
    public void canFindWords() throws IOException {
        WordDictionary dictionary = WordDictionary.loadFrom("/testwords.txt");
        List<String> words = dictionary.findWordsUsing("aab");
        assertThat(words, containsInAnyOrder("aa", "ab", "ba"));
    }

}
