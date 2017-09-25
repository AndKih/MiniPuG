/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipug;

import javax.swing.*;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class MiniPuG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        List<Context> context = contextFileReader.contextReader("database.txt");
        
        for(int ci = 0; ci < context.size(); ci++)
        {
            Context cur = context.get(ci);
            System.out.println(cur.name);
            cur.displayAllWords();
        }
        
        //Algorithm test below.
        Word testWord1 = new Word("Sitting", Word.NOUN);
        Word testWord2 = new Word("Kitten", Word.NOUN);
        Word testWord3 = new Word("Sunday", Word.NOUN);
        Word testWord4 = new Word("Saturday", Word.NOUN);
        
        int[][] dist = testWord1.leastEditDistance(testWord2);
        
        for(int i = 0; i < testWord1.getWord().length() + 1; ++i)
        {
            for(int j = 0; j < testWord2.getWord().length() + 1; ++j)
            {
                if(j != testWord2.getWord().length())
                    System.out.print(dist[i][j] + ", ");
                else
                    System.out.print(dist[i][j]);
            }
            System.out.print("\n");
        }
        
        
    }
    
}
