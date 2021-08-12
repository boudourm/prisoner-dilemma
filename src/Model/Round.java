package Model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import interfaceEtChoix.Choix;
import interfaceEtChoix.IStrategy;


public class Round {
	
	//attributs
	private Map<IStrategy , Integer> scores ;
	
	//Associations
	private Partie partie;
	private List<IStrategy> strategies;
	private Map<IStrategy , Choix> choices ;
	
	
	//Constructor
	public Round (IStrategy s1 , IStrategy s2)
	{
		
		this.strategies = new ArrayList<IStrategy>();
		this.scores = new LinkedHashMap<IStrategy, Integer>();
		scores.put(s1, 0);
		scores.put(s2, 0);
		this.choices = new LinkedHashMap<IStrategy, Choix>();
		choices.put(s1, null);
		choices.put(s2, null);
	}

	
	//Guetters & Setters

	public Map<IStrategy, Integer> getScores() {
		return scores;
	}
	public void setScores(Map<IStrategy, Integer> scores) {
		this.scores = scores;
	}
	
	
	//Methods
	public void computeScore()
	{
		IStrategy s1  = (IStrategy) this.scores.keySet().toArray()[0];
		IStrategy s2  = (IStrategy) this.scores.keySet().toArray()[1];
		
		this.choices.replace(s1,  s1.jouerCoup(null));  
		
		if(s2 == null)
		{
			if(s1.jouerCoup(null).ordinal()==0 )
			{
				//Celle qui cooepere obtient C unités C = 3
				this.scores.replace(s1, 3);
				//Celle qui cooepere obtient C unités C = 3
				this.scores.replace(s2, 3);
			}
			
			if(s1.jouerCoup(null).ordinal()==1 )
			{
				
				//Celle qui trahit obtient P unités P = 1
				this.scores.replace(s1, 1);
				//Celle qui trahit obtient P unités P = 1
				this.scores.replace(s2, 1);

			}
		}
		else
		{
			this.choices.replace(s2, s2.jouerCoup(null)); 

			if(s1.jouerCoup(null).ordinal()==0 && s2.jouerCoup(null).ordinal()==0)
			{
				//Celle qui cooepere obtient C unités C = 3
				this.scores.replace(s1, 3);
				//Celle qui cooepere obtient C unités C = 3
				this.scores.replace(s2, 3);
			}
			
			if(s1.jouerCoup(null).ordinal()==0 && s2.jouerCoup(null).ordinal()==1)
			{
				//Celle qui cooepere obtient D unités D = 0
				this.scores.replace(s1, 0);
				//Celle qui trahit obtient T unités T = 5
				this.scores.replace(s2, 5);
			}
			
			if(s1.jouerCoup(null).ordinal()==1 && s2.jouerCoup(null).ordinal()==0)
			{
				
				//Celle qui trahit obtient T unités T = 5
				this.scores.replace(s1, 5);
				//Celle qui cooepere obtient D unités D = 0
				this.scores.replace(s2, 0);
			}
			
			if(s1.jouerCoup(null).ordinal()==1 && s2.jouerCoup(null).ordinal()==1)
			{
				
				//Celle qui trahit obtient P unités P = 1
				this.scores.replace(s1, 1);
				//Celle qui trahit obtient P unités P = 1
				this.scores.replace(s2, 1);
			}
			
		}
		
		
	}
	
}
