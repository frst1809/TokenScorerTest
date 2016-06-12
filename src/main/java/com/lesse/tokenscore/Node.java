package com.lesse.tokenscore;

import java.util.ArrayList;
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
    
    public int countOutLink(Token t) {
        int cnt = 0;
        for (NodeLink link : outLink) {
            if (link.getNode().getToken().equals(t)) {
                cnt++;
            }
        }
        
        return cnt;
    }
    
    public int getOutLinkSize() {
        return outLink.size();
    }
    
    

    private Token token;
    List<NodeLink> outLink;
    
    private double weight;
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public Node(Token t) {
        this.token = t;
        this.outLink = new ArrayList<NodeLink>();
    }
    
    public Node() {
        this.outLink = new ArrayList<NodeLink>();
    }
    
    
    @Override
    public String toString() {
        return token.toString()+"->"+outLink.size();
    }   
}
