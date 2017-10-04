/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipug;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Andreas
 */
public class Sentence {
    
    private Context context;
    private List<Word> content = new LinkedList<>();
    private static final char PERIOD = '.', QUESTION = '?', SHOUTIT = '!';
    
    public Sentence()
    {
        
    }
    
    public Sentence(Sentence copyThis)
    {
        this.context = new Context(copyThis.getContext());
        content.addAll(copyThis.getWordList());
    }
    
    public Sentence(Context context)
    {
        this.context = context;
    }
    
    public Sentence(String words)
    {
        stringToSentence(words);
    }
    
    public Sentence(String words, Context context)
    {
        stringToSentence(words);
        this.context = new Context(context);
    }
    
    public void stringToSentence(String convertThis)
    {
        String word = "";
//        System.out.println("Using this string: " + convertThis);
        for(int ids = 0; ids < convertThis.length(); ++ids)
        {
            char curChar = convertThis.charAt(ids);
//            System.out.println("Current char: " + curChar);
            if(curChar != ' ' && curChar != ',' && curChar != '.' && curChar != '!' && curChar != '?')
            {
//                System.out.println("Current word: " + word);
                word += curChar;
            }
            else if(!word.equals(""))
            {
//                System.out.println("Adding " + word + " to sentence.");
                content.add(new Word(word, Word.NOUN));
                word = "";
            }
            if(ids == convertThis.length() - 1 && (curChar != ' ' && curChar != ',' && curChar != '.' && curChar != '!' && curChar != '?'))
                content.add(new Word(word, Word.NOUN));
        }
    }
    
    public boolean checkGrammar()
    {
        System.out.println("Not yet implemented.");
        return false;
    }
    
    public int compareSentences(Sentence template)
    {
        int length = getLength(), totalDistance = 0;
//        System.out.println("Sentence length: " + length);
//        System.out.println("Template length: " + template.getLength());
        for(int idx = 0; idx < length; ++idx)
        {
            totalDistance += content.get(idx).leastEditDistance(template.getWordByIndex(idx));
        }
        return totalDistance;
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
    
    public void mutateWithContext()
    {
        Random random = new Random();
        int randomInt = random.nextInt(context.getLength()-1);
        Word ranWord = context.getWord(randomInt);
        int randomInt2 = random.nextInt(content.size() - 1);
        content.remove(randomInt2);
        content.add(randomInt2, ranWord);
    }
    
    public boolean hasWord(Word word)
    {
        for(int ids = 0; ids < content.size(); ++ids)
        {
            if(word.Equals(content.get(ids)))
                return true;
        }
        return false;
    }
    
    public Word getWordByIndex(int index)
    {
        return content.get(index);
    }
    
    public int getLength()
    {
        return content.size();
    }
    
    public Context getContext()
    {
        return context;
    }
    
    public List<Word> getWordList()
    {
        return content;
    }
    
    public String toString()
    {
        String result = "";
        for(int ids = 0; ids < content.size(); ++ids)
        {
            result += content.get(ids).getWord();
            if(ids != content.size() - 1)
                result += " ";
        }
        result += SHOUTIT;
        return result;
    }
    
}
