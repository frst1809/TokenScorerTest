package com.lesse.tokenscore;

public class Token {
    Integer word;
    String pos;
    
    @Override
    public int hashCode() {
        return word;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Token other = (Token) obj;
        if (pos == null) {
            if (other.pos != null)
                return false;
        } else if (!pos.equals(other.pos))
            return false;
        if (word == null) {
            if (other.word != null)
                return false;
        } else if (!word.equals(other.word))
            return false;
        return true;
    }
    
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
