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
    private final WordType wordType;
    
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
        this.content = content.toLowerCase();
        if(TYPES.contains(type))
            wordType = new WordType(type);
        else
            wordType = new WordType(-1);
    }
    
    public String getWord()
    {
        return content;
    }
    
    public int getWordType()
    {
        return wordType.returnType();
    }
    
    //Wagner-Fischer Algorithm
    public int leastEditDistance(Word otherWord)
    {
        int[][] d = new int[content.length() + 1][otherWord.getWord().length() + 1];
        d[0][0] = 0;
        for(int i = 1; i <= content.length(); ++i)
        {
            d[i][0] = i;
        }
        for(int j = 1; j <= otherWord.getWord().length(); ++j)
        {
            d[0][j] = j;
        }
        for(int i = 1; i <= content.length(); ++i)
            for(int j = 1; j <= otherWord.getWord().length(); ++j)
            {
                if(content.charAt(i - 1) == otherWord.getWord().charAt(j - 1))
                    d[i][j] = d[i - 1][j - 1];
                else
                {
                    d[i][j] = Math.min(d[i - 1][j], Math.min(d[i][j - 1], d[i - 1][j - 1])) + 1;
                }
            }
        return d[content.length()][otherWord.getWord().length()];
    }
    
    public boolean Equals(Word otherWord)
        {
            return content.equals(otherWord.getWord());
        }
    
    private class WordType
    {
        private final int type;
        
        //private final List<Integer> linkedTypes;
        
        public WordType(int type)
        {
            this.type = type;
            if(type == -1)
                System.out.println("INCORRECT TYPE, FIX IT!!!");
            //If proper grammar is implemented, use this
            /*switch(type)
            {
                case NOUN:
                    linkedTypes.add()
                    break;
                case VERB:
                    
                    break;
                case ADJECTIVE:
                    
                    break;
                case ADVERB:
                    
                    break;
                case PRONOUN:
                    
                    break;
                case PREPOSITION:
                    
                    break;
                case CONJUCTION:
                    
                    break;
                case DETERMINER:
                    
                    break;
                case EXCLAMATION:
                    
                    break;
            }*/
        }
        
        /*public boolean compareType(int otherType)
        {
            
        }*/
        
        public int returnType()
        {
            return type;
        }
        
    }
    
}
