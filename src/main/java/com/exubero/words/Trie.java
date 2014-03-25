package com.exubero.words;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class Trie {

    private final String character;
    private final Trie[] children;
    private boolean isWord = false;

    public Trie() {
        this("");
    }

    private Trie(String c) {
        children = new Trie[26];
        character = c;
    }

    public void addWord(String word) {
        char firstChar = word.charAt(0);
        Trie childNode = childNodeFor(firstChar);
        if (word.length() > 1) {
            childNode.addWord(word.substring(1));
        } else {
            childNode.isWord = true;
        }

    }

    public boolean containsWord(String possibleWord) {
        if (possibleWord.length() == 0) {
            return isWord;
        }

        char firstChar = possibleWord.charAt(0);
        Trie childNode = children[(firstChar - 'a')];
        if (childNode == null) {
            return false;
        }
        return childNode.containsWord(possibleWord.substring(1));
    }

    private Trie childNodeFor(char theChar) {
        int charPos = theChar - 'a';
        if (charPos < 0 || charPos >= children.length) {
            throw new IllegalArgumentException("'" + theChar + "' is not a valid alphabetic character");
        }
        if (children[charPos] == null) {
            children[charPos] = new Trie(Character.toString(theChar));
        }
        return children[charPos];
    }

    public interface WordVisitor {
        void word(String theWord);
    }

    public void visitWords(String prefix, WordVisitor visitor) {
        String nodeWord = prefix + character;

        if (isWord) {
            visitor.word(nodeWord);
        }

        for (Trie child : children) {
            if (child != null) {
                child.visitWords(nodeWord, visitor);
            }
        }
    }

    public List<String> words() {
        List<String> visitedWords = Lists.newArrayList();
        visitWords("", (theWord) -> {
            visitedWords.add(theWord);
        });
        return Collections.unmodifiableList(visitedWords);
    }

}
