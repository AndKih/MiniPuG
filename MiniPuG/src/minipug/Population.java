/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipug;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class Population {
    
    private List<Sentence> individuals = new ArrayList<>();
    private Sentence template;
    
    public Population()
    {
        
    }
    
    public Population(Sentence template)
    {
        this.template = new Sentence(template);
    }
    
    public Population(Population copyThis)
    {
        template = new Sentence(copyThis.getTemplate());
        individuals.addAll(copyThis.getPop());
    }
    
    //Adding functions
    public void addIndividual(Sentence sen)
    {
        individuals.add(sen);
    }
    
    public int getPopSize()
    {
        return individuals.size();
    }
    
    public Sentence getTemplate()
    {
        return template;
    }
    
    public List<Sentence> getPop()
    {
        return individuals;
    }
    
    public boolean checkWordPresence(Word word)
    {
        for(int idi = 0; idi < individuals.size(); ++idi)
        {
            if(individuals.get(idi).hasWord(word))
                return true;
        }
        return false;
    }
    
    public Sentence getIndividual(int index)
    {
        return individuals.get(index);
    }
    
    public Sentence bestIndividual()
    {
        Sentence result;
        int savedIndex = -1, lastDistance = 100000;
        for(int idx = 0; idx < getPopSize(); ++idx)
        {
            int curDistance = individuals.get(idx).compareSentences(template);
            if(curDistance < lastDistance)
            {
                lastDistance = curDistance;
                savedIndex = idx;
            }
        }
        result = individuals.get(savedIndex);
        return result;
    }
    
}
