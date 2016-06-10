package com.lesse.tokenscore;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetector {

    public static SentenceDetector getInstance() {
        return instance;
    }
    
    private static SentenceDetector instance = new SentenceDetector();
    
    private SentenceDetector() {
        InputStream modelIn = null;
        try {
            modelIn = SentenceDetector.class.getResourceAsStream("/en-sent.bin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            model = new SentenceModel(modelIn);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                } catch (IOException e) {
                }
            }
        }
        
        detectorME = new SentenceDetectorME(model);
    }
    
    SentenceModel model;
    private static SentenceDetectorME detectorME;
    
    public static String[] detect(String str) {
        return detectorME.sentDetect(str);
    }
    
}
