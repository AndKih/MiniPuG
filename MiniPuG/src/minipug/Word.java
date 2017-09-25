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
    
    public Word(String content, String type)
    {
        this.content = content;
        wordType = type;
    }
    
    public String getWord()
    {
        return content;
    }
    
    
    
}
