/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipug;

import java.util.ArrayList;
import java.util.List;

/**
 * This class exists to store the context of a sentence.
 * Compared to a sentence, the context does not require
 * any kind of sentence structure.
 * @author Andreas
 */

public class Context {
    
    public final String name;
    private List<Word> storage = new ArrayList<>();
    
    public Context(String name)
    {
        this.name = name;
    }
    
    public Context(String name, List<Word> list)
    {
        this.name = name;
        storage.addAll(list);
    }
    
    public void addWord(Word word)
    {
        storage.add(word);
    }
    
    public void removeWord(Word word)
    {
        if(storage.contains(word))
            storage.remove(word);
        else
            System.out.println("Context does not contain this word!");
    }
    
    public void removeWord(int index)
    {
        if(storage.size() > index)
            storage.remove(index);
        else
            System.out.println("Context is smaller than " + index + "!");
    }
    
    public boolean relatedTo(Word word)
    {
        return storage.contains(word);
    }
    
    public void displayAllWords()
    {
        for(int idw = 0; idw < storage.size(); ++idw)
        {
            System.out.print(storage.get(idw).getWord());
            if(idw < storage.size()-1)
            {
                System.out.print(", ");
            }
        }
        
    }
}
