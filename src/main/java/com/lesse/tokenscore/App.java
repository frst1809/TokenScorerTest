package com.lesse.tokenscore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import com.lesse.tokenscore.nlp.DependencyTree;
import com.lesse.tokenscore.nlp.SentenceDetector;

public class App 
{
    static Dictionary dict = Dictionary.getInstance();
    
    public static void main(String[] args) {
        String article = "The Coinage Act of 1873 declared that US dollar coins struck from silver bullion were no longer considered legal tender, thus placing the nation firmly on the gold standard and ending bimetallism. In 1869, silver was expensive, and not much of it was being presented at the Mint to be struck into coins, but Deputy Comptroller of the Currency John Jay Knox and others foresaw that cheaper ore from the Comstock Lode and elsewhere would soon became available. To replace the outdated Mint Act of 1837, Knox drafted a bill that took nearly three years to pass. It was rarely mentioned during Congressional debates that the bill would end bimetallism, though this was not concealed. The bill was finally signed into law by President Ulysses S. Grant. When silver prices dropped in 1876, producers sought to have their bullion struck at the Mint, only to learn that this was no longer possible. The resulting political controversy lasted the remainder of the century, pitting those who valued the deflationary gold standard against those who called the Act the \"Crime of '73\", believing the free coinage of silver to be necessary for economic prosperity.";
        
        String [] setences = SentenceDetector.detect(article);
        
        List<Triple> allDep = new ArrayList<Triple>();
        
        for (String s : setences) {
            allDep.addAll(DependencyTree.getTree(s));
        }
        
        for(Triple t : allDep) {
            System.out.println(t.toString());
        }
        
        // calculate word importance using pageRank algorithm
        // dependency is link
        
        // Issues
        // 1) How to do sense selection
        // 2) how to link pronoun to noun, this linkage is very important.
        // 3) need to merge compound word : noun + noun, "a lot of"
        // 4) And so on....
        
        // Anyway, calculate
       
        // structure graph
        List<Node> nodes = new ArrayList<Node>();
        for (Triple t : allDep) {
            
            Node subNode = new Node(t.sub);
            int subNodeIndex = nodes.indexOf(subNode);
            if (subNodeIndex < 0) {
                subNodeIndex = nodes.size();
                nodes.add(subNode);
            } else {
                subNode = nodes.get(subNodeIndex);
            }
             
            Node objNode = new Node(t.obj);
            int objNodeIndex = nodes.indexOf(objNode);
            if (objNodeIndex < 0) {
                objNodeIndex = nodes.size();
                nodes.add(objNode);
            } else {
                objNode = nodes.get(objNodeIndex);
            }
            
            NodeLink link = new NodeLink();
            link.setNode(objNode);            
            link.setDependendcy(t.rel);
            
            subNode.addNodeLink(link);
        }
        
        System.out.println("Nodes Count : "+nodes.size());
        
        RealMatrix mat = MatrixUtils.createRealMatrix(nodes.size(), nodes.size());
        
        for (int i=0 ; i<nodes.size(); i++) {
            Node node1 = nodes.get(i);
            
            for (int j=0; j < nodes.size(); j++) {
                Node node2 = nodes.get(j);

                mat.setEntry(node1.getToken().word, 
                             node2.getToken().word, 
                             node1.getOutLinkSize() > 0 ?
                                        node1.countOutLink(node2.getToken())/(node1.getOutLinkSize()*1.0d):0.0d);
            }
        }
        
        
        
        RealMatrix matRank = MatrixUtils.createRealMatrix(nodes.size(), 1);
        
        for (int i=0 ; i < nodes.size(); i++) {
            matRank.setEntry(i, 0, 1.0d/(nodes.size()*1.0d));
        }
        
        //Test calculate
        int iter = 0;
        while ((iter++) < 10) {
            matRank = mat.multiply(matRank);
        }
        
        for (int i=0 ; i < nodes.size(); i++) {
            nodes.get(i).setWeight(matRank.getEntry(i, 0));
        }
        
        Collections.sort(nodes, new Comparator<Node>(){

            @Override
            public int compare(Node o1, Node o2) {
                return Double.compare(o1.getWeight(), o2.getWeight());
            }
            
        });
        
        
        for (int i = nodes.size()-1 ; i > nodes.size()-10 ; i--) {
            System.out.println(
                    "weight : "+nodes.get(i).getWeight()+", "+
                    dict.getWord(nodes.get(i).getToken().word));
        }
            
       
    }
}
