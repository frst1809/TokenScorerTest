package com.lesse.tokenscore;

public class Token {
    Integer word;
    String pos;
    
    public Integer getWord() {
        return word;
    }
    public void setWord(Integer word) {
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
