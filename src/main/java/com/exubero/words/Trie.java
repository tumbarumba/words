package com.exubero.words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trie {

    private static final class TrieNode {
        private final String character;
        private final TrieNode[] children;
        private boolean isWord = false;

        public TrieNode(String c) {
            children = new TrieNode[26];
            character = c;
        }

        public void addWord(String word) {
            char firstChar = word.charAt(0);
            TrieNode childNode = childNodeFor(firstChar);
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
            TrieNode childNode = children[(firstChar - 'a')];
            if (childNode == null) {
                return false;
            }
            return childNode.containsWord(possibleWord.substring(1));
        }

        private TrieNode childNodeFor(char theChar) {
            int charPos = theChar - 'a';
            if (charPos < 0 || charPos >= children.length) {
                throw new IllegalArgumentException("'" + theChar + "' is not a valid alphabetic character");
            }
            if (children[charPos] == null) {
                children[charPos] = new TrieNode(Character.toString(theChar));
            }
            return children[charPos];
        }

        public void addWordsTo(List<String> words, String baseWord) {
            String nodeWord = baseWord + character;
            if (isWord) {
                words.add(nodeWord);
            }
            for(TrieNode childNode : children) {
                if (childNode != null) {
                    childNode.addWordsTo(words, nodeWord);
                }
            }
        }
    }

    private final TrieNode root = new TrieNode("");

    public Trie() {
    }

    public void addWord(String word) {
        root.addWord(word.toLowerCase());
    }

    public boolean containsWord(String possibleWord) {
        return root.containsWord(possibleWord);
    }

    public List<String> words() {
        List<String> allWords = new ArrayList<String>();
        root.addWordsTo(allWords, "");
        return Collections.unmodifiableList(allWords);
    }

}
