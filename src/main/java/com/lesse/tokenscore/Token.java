package com.lesse.tokenscore;

public class Token {
    String word;
    String pos;
    
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getPos() {
        return pos;
    }
    public void setPos(String pos) {
        this.pos = pos;
    }
    
    @Override
    public String toString() {
        return word+"("+pos+")";
    }
}
