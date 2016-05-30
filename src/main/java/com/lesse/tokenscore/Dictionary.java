package com.lesse.tokenscore;

import java.util.ArrayList;

public class Dictionary {
    private Dictionary() {
        words = new ArrayList<String>();
    }
    
    private Dictionary dict;
    
    public Dictionary getInstance() {
        if (dict == null) {
            dict = new Dictionary();
        }
        return dict;
    }
    
    //This is just temporary version
    private ArrayList<String> words;  
    
    public boolean isStopword(String word) {
        return false;
    }
    
    public int putWord(String word) {
        if (isStopword(word)) {
            return -1;
        }
        
        if (words.contains(word)) {
            return words.indexOf(word);
        }
        
        words.add(word);
        return words.size()-1;
    }
    
    public int getWordIndex(String word) {
        if (isStopword(word)) {
            return -1;
        }
        
        if (words.contains(word)) {
            return words.indexOf(word);
        }
        
        return -1;
    }
    
}
