package com.lesse.tokenscore.nlp;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lesse.tokenscore.Dictionary;
import com.lesse.tokenscore.Triple;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

public class DependencyTree {

    private static TreebankLanguagePack tlp = new PennTreebankLanguagePack();
    private static GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
    
    
    
    public static List<Triple> getTree(String sentence) {
        LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
        lp.setOptionFlags(new String[]{"-maxLength", "500", "-retainTmpSubcategories"});
        TokenizerFactory<CoreLabel> tokenizerFactory =
                PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
        List<CoreLabel> wordList = tokenizerFactory.getTokenizer(new StringReader(sentence)).tokenize();
        
        Tree tree = lp.apply(wordList);
        Collection<TypedDependency> tdl = gsf.newGrammaticalStructure(tree)
                                                .typedDependencies();
        
        Dictionary dict = Dictionary.getInstance();
        
        List<Triple> depTree = new ArrayList<Triple>();
        for (TypedDependency td : tdl) {            
            Triple t = new Triple();
            t.sub.setWord(dict.putWord(td.gov().word()));
            t.sub.setPos(td.gov().tag());
            
            t.rel = td.reln().getShortName();
            
            t.obj.setWord(dict.putWord(td.dep().word()));
            t.obj.setPos(td.dep().tag());
            
            depTree.add(t);
        }
        
        return depTree;
    }
    
}
