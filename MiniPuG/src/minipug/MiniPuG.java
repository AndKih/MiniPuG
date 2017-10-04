/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipug;

import java.awt.Container;
import javax.swing.*;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class MiniPuG{
    
    //GUI Variables.
    JPanel mainPanel;
    JComboBox contextPicker;
    JButton showContext, selectSentence;
    List<JComponent> componentList;
    JTextField inputSentence;
    String input;
    
    private static String TEMPLATE_1 = "The early bird might get the worm, but the second mouse gets the cheese.";
    
    //Static variables.
    static List<Context> contextList;
    
    //Constructor
    public MiniPuG()
    {
        initComponentValues();
    }
    
    private void initComponentValues()
    {
        mainPanel = new JPanel();
        contextPicker = new JComboBox(getContextNames());
        contextPicker.setEditable(false);
        showContext = new JButton("Show selected context!");
        selectSentence = new JButton("Select sentence!");
        initButtons();
        componentList = new ArrayList<>();
        componentList.add(new JLabel("SentenceInput"));
        inputSentence = new JTextField(50);
        inputSentence.setEditable(true);
        componentList.add(inputSentence);
        componentList.add(new JLabel("ComboBoxes"));
        componentList.add(contextPicker);
        componentList.add(new JLabel("Buttons"));
        componentList.add(showContext);
        componentList.add(selectSentence);
        for(int idc = 0; idc < componentList.size(); ++idc)
            mainPanel.add(componentList.get(idc));
    }
    
    private void initButtons()
    {
        showContext.addActionListener((ActionEvent) -> {
            System.out.println("Selected context: " + String.valueOf(contextPicker.getSelectedItem()));
        });
        selectSentence.addActionListener((ActionEvent) -> {
            input = String.valueOf(inputSentence.getText());
            System.out.println("Selected: " + input);
        });
    }
    
    private JComponent getMainComponent()
    {
        return mainPanel;
    }
    
//    private JComponent[] getList()
//    {
//        JComponent[] result = new JComponent[componentList.size()];
//        for(int idc = 0; idc < componentList.size(); ++idc)
//            result[idc] = componentList.get(idc);
//        return result;
//    }
    
    private String[] getContextNames()
    {
        String[] result = new String[contextList.size()];
        for(int ids = 0; ids < contextList.size(); ++ids)
            result[ids] = contextList.get(ids).name;
        return result;
    }
    
    private static void initUI()
    {
        MiniPuG mainObject = new MiniPuG();
        
        JFrame frame = new JFrame("TESTING GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainObject.getMainComponent());
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        List<String> tmpSentence = contextFileReader.sentenceReader("SentenceDatabase.txt", 14);
        contextList = contextFileReader.contextReader("database3.txt");
        Context mainContext = contextList.get(0);
//        System.out.println(mainContext.name);
//        System.out.println("Size of list: " + contextList.size());
        for(int i = 0; i < tmpSentence.size(); i++)
        {
            System.out.println("New sentence below.");
            System.out.println(tmpSentence.get(i));
        }
//        System.out.println(mainContext.name);
        Sentence templateSentence = new Sentence(TEMPLATE_1, mainContext);
//        System.out.println("Sentence: " + templateSentence.toString());
//        System.out.println(mainContext.name);
        Population database = new Population(templateSentence);
//        System.out.println(mainContext.name);
        for(int ids = 0; ids < tmpSentence.size(); ++ids)
        {

//            System.out.println(mainContext.name);
            Sentence addThis = new Sentence(tmpSentence.get(ids), mainContext);
            System.out.println("Sentence length: " + addThis.getLength());
            System.out.println("Sentence: " + addThis.toString());
            database.addIndividual(addThis);

//            System.out.print(tmpSentence.get(i));
//            String tmpString = tmpSentence.get(i);
//            int n = 1;
//            for(int j = 0; j < tmpString.length(); j++)
//            {
//                char c = tmpString.charAt(j);
//                if(c == ' ')
//                {
//                    n++;
//                }
//            }
//            System.out.println(": " + n + " words.");
//            n=0;

        }
        
        
        
        int generationNumber = 0;
        
        while(database.bestIndividual().compareSentences(templateSentence) != 0)
        {
            ++generationNumber;
//            System.out.println("Current generation: " + generationNumber);
//            System.out.println("Current best individual: " + database.bestIndividual().toString());
            if(generationNumber % 10000 == 0 || generationNumber == 1)
            { 
                System.out.println("Current generation: " + generationNumber);
                System.out.println("Current best individual: " + database.bestIndividual().toString());
                System.out.println("Distance: " + database.bestIndividual().compareSentences(templateSentence));
            }
            database = GeneticAI.breedNewGeneration(database);
        }
        
        System.out.println("Evolution complete!!");
        System.out.println("Generation: " + generationNumber);
        System.out.println("Best individual: " + database.bestIndividual().toString());
        
//        contextList = contextFileReader.contextReader("database2.txt");
//        
//        for(int ci = 0; ci < contextList.size(); ci++)
//        {
//            Context cur = contextList.get(ci);
//            System.out.print(cur.name +"\n  - ");
//            cur.displayAllWords();
//            System.out.println("\n");
//        }
        
        //Algorithm test below.
//        Word testWord1 = new Word("Sitting", Word.NOUN);
//        Word testWord2 = new Word("Kitten", Word.NOUN);
//        Word testWord3 = new Word("Sunday", Word.NOUN);
//        Word testWord4 = new Word("Saturday", Word.NOUN);
//        
//        int dist = testWord1.leastEditDistance(testWord2);
//        System.out.println("Distance: " + dist);
        
//        SwingUtilities.invokeLater(new Runnable(){
//            public void run()
//            {
//                initUI();
//            }
//        });
        
//        for(int i = 0; i < testWord1.getWord().length() + 1; ++i)
//        {
//            for(int j = 0; j < testWord2.getWord().length() + 1; ++j)
//            {
//                if(j != testWord2.getWord().length())
//                    System.out.print(dist[i][j] + ", ");
//                else
//                    System.out.print(dist[i][j]);
//            }
//            System.out.print("\n");
//        }
        
        
    }
    
}
