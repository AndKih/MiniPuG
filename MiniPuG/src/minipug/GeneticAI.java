/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Andreas
 */
public class GeneticAI {
    
    private static double mutationRate = 0.015;
    private static double breedChance = 0.5;
    private static boolean keepBest = true;
    private static int selectionSize = 5;
    
    public static Population breedNewGeneration(Population pop)
    {
        Population result = new Population(pop.getTemplate());
//        System.out.println("ifKeepBest");
        if(keepBest)
        {
            Sentence bestSentence = pop.bestIndividual();
            result.addIndividual(bestSentence);
        }
        
        int elitismOffset = 0;
        if(keepBest)
            elitismOffset = 1;
//        System.out.println("Breeding stage");
//        System.out.println("Population size: " + pop.getPopSize());
        for(int idx = elitismOffset; idx < pop.getPopSize(); ++idx)
        {
            Sentence sen1 = pickIndividual(pop);
            Sentence sen2 = pickIndividual(pop);
            Sentence newSen = breed(sen1, sen2);
            result.addIndividual(newSen);
        }
//        System.out.println("Mutation stage");
        for(int idx = elitismOffset; idx < result.getPopSize(); ++idx)
        {
            mutate(result.getIndividual(idx));
        }
        
        return result;
    }
    
    private static Sentence breed(Sentence sen1, Sentence sen2)
    {
        Sentence result = new Sentence(sen1.getContext());
        int length = sen1.getLength();
        List<Integer> indices1 = generateIntegerList(sen1.getLength());
        List<Integer> indices2 = generateIntegerList(sen2.getLength());
        for(int idx = 0; idx < length; ++idx)
        {
            if(Math.random() <= breedChance)
            {
                result.addWord(sen1.getWordByIndex(indices1.get(idx)));
            }
            else
            {
                result.addWord(sen2.getWordByIndex(indices2.get(idx)));
            }
        }
        return result;
    }
    
    private static List<Integer> generateIntegerList(int length)
    {
        List<Integer> result = new ArrayList<>();
        Random random = new Random();
        for(int idx = 0; idx < length; ++idx)
        {
            result.add(idx);
        }
        Collections.shuffle(result);
        return result;
    }
    
    private static void mutate(Sentence sen)
    {
        for(int idx = 0; idx < sen.getLength(); ++idx)
        {
            if(Math.random() <= mutationRate)
            {
                sen.mutateWithContext();
            }
        }
    }
    
    private static Sentence pickIndividual(Population pop)
    {
        Sentence result;
        Population pickedSentences = new Population(pop.getTemplate());
//        System.out.println("PickIndividualPRE");
        for(int idx = 0; idx < selectionSize; ++idx)
        {
            int randomIndex = (int)(Math.random()*pop.getPopSize());
            pickedSentences.addIndividual(pop.getIndividual(randomIndex));
        }
//        System.out.println("PickIndividualPOST");
        result = pickedSentences.bestIndividual();
        return result;
    }
    
}
