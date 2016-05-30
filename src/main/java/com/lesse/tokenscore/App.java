package com.lesse.tokenscore;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String sentence = "Orange juice contains a lot of vitamin C. ";
        
        List<Triple> deptree = DependencyTree.getTree(sentence);
        
        for(Triple t : deptree) {
            System.out.println(t.toString());
        }
        
        
        
        
        
        
    }
}
