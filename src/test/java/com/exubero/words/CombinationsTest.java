package com.exubero.words;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class CombinationsTest {
    @Test
    public void testCombinations() {
        List<String> combinations = Combinations.of("bac");
        assertThat(combinations, contains("a", "ab", "abc", "ac", "b", "bc", "c"));
    }
}
