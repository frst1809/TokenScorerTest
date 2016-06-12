package com.lesse.tokenscore;

public class NodeLink {
    public Node getNode() {
        return node;
    }
    public void setNode(Node node) {
        this.node = node;
    }
    public String getDependendcy() {
        return dependendcy;
    }
    public void setDependendcy(String dependendcy) {
        this.dependendcy = dependendcy;
    }
    
    Node node;
    String dependendcy;
}
