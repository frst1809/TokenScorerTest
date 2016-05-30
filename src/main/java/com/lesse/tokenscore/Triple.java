package com.lesse.tokenscore;

public class Triple {
    
    public Triple() {
        sub = new Token();
        obj = new Token();
    }
    
    public Token sub;
    public String rel;
    public Token obj;
    
    @Override
    public String toString() {
        return sub.toString()+"-"+rel+"->"+obj.toString();
    }
}
