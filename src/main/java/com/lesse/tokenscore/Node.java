package com.lesse.tokenscore;

import java.util.List;

public class Node {

    @Override
    public int hashCode() {
        return token.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (token == null) {
            if (other.token != null)
                return false;
        } else if (!token.equals(other.token))
            return false;
        return true;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
    
    public void addNodeLink(NodeLink link) {
        this.outLink.add(link);
    }
    
    

    private Token token;
    
    List<NodeLink> outLink;
    
    public Node(Token t) {
        this.token = t;
    }
    
    public Node() {}
    
    
    
}
