/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipug;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class Word {
    
    private final String content;
    private final int wordType;
    
    public static final int NOUN = 1001, VERB = 1002, ADJECTIVE = 1003,
                            ADVERB = 1004, PRONOUN = 1005, PREPOSITION = 1006,
                            CONJUCTION = 1007, DETERMINER = 1008, EXCLAMATION = 1009;
    private static final List<Integer> TYPES = new ArrayList<>();
    
    static{
        TYPES.add(NOUN);
        TYPES.add(VERB);
        TYPES.add(ADJECTIVE);
        TYPES.add(ADVERB);
        TYPES.add(PRONOUN);
        TYPES.add(PREPOSITION);
        TYPES.add(CONJUCTION);
        TYPES.add(DETERMINER);
        TYPES.add(EXCLAMATION);
    }
    
    public Word(String content, int type)
    {
        this.content = content;
        if(TYPES.contains(type))
            wordType = type;
        else
            wordType = -1;
    }
    
    public String getWord()
    {
        return content;
    }
    
    public int getWordType()
    {
        return wordType;
    }
    
    private class WordType
    {
        private final int type;
        
        private final List<Integer> linkedTypes;
        
        public WordType(int type)
        {
            this.type = type;
            switch(type)
            {
                case NOUN:
                    
                    break;
                case VERB:
                    
                    break;
            }
        }
        
        public boolean compareType(int otherType)
        {
            switch
        }
        
    }
    
}
