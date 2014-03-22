package com.exubero.words;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EnableWordsTest {
    @Test
    public void canLoadAllWords() throws IOException {
        WordDictionary dictionary = WordDictionary.loadFrom("/enable1.txt");
        assertThat(dictionary.words().size(), equalTo(172820));
    }
}
