/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipug;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class Sentence {
    
    private Context context;
    private List<Word> content = new LinkedList<>();
    private static final char PERIOD = '.', QUESTION = '?', SHOUTIT = '!';
    
    public Sentence(Context context)
    {
        this.context = context;
    }
    
    public boolean checkGrammar()
    {
        System.out.println("Not yet implemented.");
        return false;
    }
    
    public void addWord(Word word)
    {
        content.add(word);
    }
    
    public void addWord(int index, Word word)
    {
        content.add(index, word);
    }
    
    public void removeWord(Word word)
    {
        if(content.contains(word))
            content.remove(word);
        else
            System.out.println("Sentence does not contain " + word.getWord() + "!");
    }
    
    public void removeWordByIndex(int index)
    {
        if(content.size() > index)
            content.remove(index);
        else
            System.out.println("Sentence is smaller than " + index + "!");
    }
    
    public String toString()
    {
        String result = "";
        for(int ids = 0; ids < content.size(); ++ids)
        {
            result += content.get(ids).getWord();
        }
        result += SHOUTIT;
        return result;
    }
    
}
