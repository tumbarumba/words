package com.exubero.words;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class TrieTest {
    @Test
    public void canAddAWord() {
        Trie trie = new Trie();

        trie.addWord("word");

        assertThat(trie.words(), containsInAnyOrder("word"));
    }
}
