/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipug;

/**
 *
 * @author Andreas
 */
public class Word {
    
    private final String content;
    private final String wordType;
    
    public static final int NOUN = 1001, VERB = 1002, ADJECTIVE = 1003,
                            ADVERB = 1004, PRONOUN = 1005, PREPOSITION = 1006,
                            CONJUCTION = 1007, DETERMINER = 1008, EXCLAMATION = 1009;
    
    public Word(String content, String type)
    {
        this.content = content;
        wordType = type;
    }
    
    public String getWord()
    {
        return content;
    }
    
    public String getWordType()
    {
        return wordType;
    }
    
}
