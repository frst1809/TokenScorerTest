package com.lesse.tokenscore;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    static SentenceDetector sentenceDetector = SentenceDetector.getInstance();
    
    public static void main(String[] args) {
        String article = "The Coinage Act of 1873 declared that US dollar coins struck from silver bullion were no longer considered legal tender, thus placing the nation firmly on the gold standard and ending bimetallism. In 1869, silver was expensive, and not much of it was being presented at the Mint to be struck into coins, but Deputy Comptroller of the Currency John Jay Knox and others foresaw that cheaper ore from the Comstock Lode and elsewhere would soon became available. To replace the outdated Mint Act of 1837, Knox drafted a bill that took nearly three years to pass. It was rarely mentioned during Congressional debates that the bill would end bimetallism, though this was not concealed. The bill was finally signed into law by President Ulysses S. Grant. When silver prices dropped in 1876, producers sought to have their bullion struck at the Mint, only to learn that this was no longer possible. The resulting political controversy lasted the remainder of the century, pitting those who valued the deflationary gold standard against those who called the Act the \"Crime of '73\", believing the free coinage of silver to be necessary for economic prosperity.";
        
        String [] setences = sentenceDetector.detect(article);
        
        List<Triple> allDep = new ArrayList<Triple>();
        
        for (String s : setences) {
            allDep.addAll(DependencyTree.getTree(s));
        }
        
        //for(Triple t : allDep) {
        //    System.out.println(t.toString());
        //}
        
      
        //Dictionary dict = Dictionary.getInstance();
        
        //dict.printAllWord();
    }
}
