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
        return leastEditSentence(template, this);
    }
    public static int leastEditSentence(Sentence s1, Sentence s2)
    {
        
        int[][] dist = new int[s1.getLength()+1][s2.getLength()+1];
        for(int i = 0; i <= s1.getLength(); i++)
        {
            dist[i][0] = i;
        }
        for(int j = 0; j <= s2.getLength(); j++)
        {
            dist[0][j] = j;
        }
        for(int i = 1; i <= s1.getLength(); i++)
        {
            for(int j = 1; j <= s2.getLength(); j++)
            {
                int tmpCost = 0;
                
                if(!s1.getWordByIndex(i-1).Equals(s2.getWordByIndex(j-1)))
                {
                    tmpCost = 1;
                }
                dist[i][j] = Math.min(Math.min(
                        dist[i-1][j]+1, 
                        dist[i][j-1]+1), 
                        dist[i-1][j-1] + tmpCost);
            }
        }
        return dist[s1.getLength()][s2.getLength()];
    }
    private int compareSentences2(Sentence template)
    {
        int sentenceLengthCost = 3;
        int length = getLength(), totalDistance = 0;
//        System.out.println("Sentence length: " + length);
//        System.out.println("Template length: " + template.getLength());
        if(template.getLength() >= getLength())
        {
            for(int idx = 0; idx < template.getLength(); ++idx)
            {
                double xSample = (double)idx/template.getLength(); //scale to 0-1
                double ySample = xSample * getLength(); //scale up to 0-length

                int idy = (int)(ySample+0.5);
//                if(idy >=  getLength())
//                    System.out.println("ERRORS AND SHIT! "+idy +", " +getLength());
                idy = Math.min(idy, getLength()-1);
    //            System.out.println("ID: " + idx + ", " + idy);
                totalDistance += content.get(idy).
                        leastEditDistance(template.getWordByIndex(idx));
            }
        }
        else
        {
            for(int idy = 0; idy < getLength(); idy++)
            {
                double ySample = (double)idy/getLength();
                double xSample = ySample * template.getLength();
                
                int idx = (int)(xSample + 0.5);
                
                idx = Math.min(idx, template.getLength()-1);
                
                totalDistance += content.get(idy).
                        leastEditDistance(template.getWordByIndex(idx));
            }
        }
        
        totalDistance += Math.abs(template.getLength() - getLength()) * sentenceLengthCost;
//        for(int idx = 0; idx < length; ++idx)
//        {
//            for(int idy = 0; idy < template.getLength(); ++idy)
//            {
//                totalDistance += content.get(idx).leastEditDistance(template.getWordByIndex(idy));
//            }
//        }
        return totalDistance;
    }
    
    private int compareSentences3(Sentence template)
    {
        int sentenceLengthCost = 3;
        int totalDistance = 0;
        
        for(int idx = 0; idx < getLength(); idx++)
        {
            for(int idy = 0; idy < template.getLength(); idy++)
            {
                totalDistance += content.get(idx).
                        leastEditDistance(template.getWordByIndex(idy));
            }
        }
        totalDistance += Math.abs(template.getLength() - getLength()) * sentenceLengthCost;
        
        return totalDistance;
    }
    public int compareSentencesAndreas(Sentence template)
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
        int randomInt2 = random.nextInt(Math.max(content.size() - 1, 1));
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
