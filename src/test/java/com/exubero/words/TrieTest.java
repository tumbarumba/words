package com.exubero.words;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class TrieTest {
    @Test
    public void canAddAWords() {
        Trie trie = new Trie();

        trie.addWord("woo");
        trie.addWord("wood");
        trie.addWord("word");
        trie.addWord("words");

        assertThat(trie.words(), contains("woo", "wood", "word", "words"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void cantAddNonAlphabeticCharacters() {
        Trie trie = new Trie();

        trie.addWord("a1");
    }
}
