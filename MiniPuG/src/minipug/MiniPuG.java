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
            System.out.println("");
        }
        
    }
    
}
