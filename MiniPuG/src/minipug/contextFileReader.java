/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipug;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Valdemar
 */
public class contextFileReader {
    
    //snabb ändring
    
    public static List<Context> contextReader(String adress) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(adress));
        List<Context> contextList = new ArrayList<>();
        
        String line;
        //read every line
        //each line is a context
        while ((line = br.readLine()) != null) {
            List<Word> wl = new ArrayList<>();// a new wordlist for each line
            // read every word
            String word = ""; //current word from line
            String wordType = ""; //current type from line
            
            String contextName = "";
            
            //words from line
            for(int iw = 0; iw < line.length(); iw++)
            {
                char c = line.charAt(iw);
                
                //blankspace resets word
                if(c == ' ')
                {
                    //if a word has been read, add to wordlist
                    if(word.length() > 0)
                    {
                        //if no contextname is saved then word is name
                        if(contextName.length() > 0)
                        {
                            int t = Integer.parseInt(wordType);
                            wl.add(new Word(word, t));
                        }
                        else
                        {
                            contextName = word;
                        }
                        
                    }
                    word = "";
                    wordType = "";
                }
                else
                {
                    //if number add to type, else add to word
                    if(Character.isDigit(c))
                    {
                        wordType += c;
                    }
                    else
                    {
                        word += c;
                    }
                }
            }
            contextList.add(new Context(contextName, wl));
         }
        
        return contextList;
    }
    public static List<String> sentenceReader(String adress, int sentenceLength) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(adress));
        List<String> sentences = new ArrayList<>();
        
        String line;
        String tmp = "";
        int words_in_tmp = 0;
        //read every line
        //each line is a context
        while ((line = br.readLine()) != null) {
            //new line is blank space
            if(tmp.length() > 0)
            {
                tmp += " ";
            }
            for(int i = 0; i < line.length(); i++)
            {
                char cur = line.charAt(i);
                //if blankspace, new word
                if(cur == ' ')
                {
                    //see if more than 14 words in tmp
                    words_in_tmp++;
                    if(words_in_tmp >= sentenceLength)
                    {
                        sentences.add(tmp);
                        words_in_tmp = 0;
                        tmp = "";
                    }
                    else
                    {
                        tmp+=cur;
                    }
                }
                else
                {
                    tmp += cur;
                }
            }
            
        }
        
        return sentences;
    }
}
