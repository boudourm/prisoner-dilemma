package Model;
import java.util.ArrayList;
import java.util.List;

import interfaceEtChoix.IStrategy;
import strategies.StratGentille;
import strategies.StratMechant;


public class Tournoi implements Process{
	
	//attributs 
	private int nbRoundPartie;
	private List<IStrategy> strategies ;
	private List<Partie> parties;
	
	//Constructor
	public Tournoi(List<IStrategy> strategies, List<Partie> parties, int nbRoundPartie) {
		super();
		this.nbRoundPartie = nbRoundPartie;
		this.strategies = strategies;
		this.parties = parties;
	}
	
	public Tournoi(List<IStrategy> strategies , int nbRoundPartie) {
		super();
		this.nbRoundPartie = nbRoundPartie;
		this.strategies = strategies;
		this.parties = new ArrayList<Partie>();
	}

	//Guetters & Setters 
	public int getNbRoundPartie() {
		return nbRoundPartie;
	}
	public void setNbRoundPartie(int nbRoundPartie) {
		this.nbRoundPartie = nbRoundPartie;
	}
	public List<IStrategy> getStrategies() {
		return strategies;
	}
	public void setStrategies(List<IStrategy> strategies) {
		this.strategies = strategies;
	}
	public List<Partie> getParties() {
		return parties;
	}
	public void setParties(List<Partie> parties) {
		this.parties = parties;
	}
	
	
	//Methods
	public void computeGame()
	{
		while(!isFinished())
		{
			next();
			int taille = this.parties.size();
			Partie lastMatch = this.parties.get(taille-1);
			lastMatch.computeGame();
		}
	}
	
	@Override
	public void next() {
		 Partie p;
		//<<create>> Partie
		int taille = this.parties.size();
		if(taille == 0)
		{
			p = new Partie(this.strategies.get(0), null, this.nbRoundPartie);
		}
		else

		{
			IStrategy str1  = (IStrategy) this.parties.get(taille-1).getScores().keySet().toArray()[0];
			IStrategy str2  = (IStrategy) this.parties.get(taille-1).getScores().keySet().toArray()[1];
			int indexStr1 = this.strategies.indexOf(str1);
			int indexStr2 ;
			
			if(str2==null)
			{
				indexStr2 = indexStr1;
			}
			else
			{
				indexStr2 = this.strategies.indexOf(str2);
			}

			
			if(indexStr2 == this.strategies.size()-1) 
			{
				p = new Partie(this.strategies.get(indexStr1+1), this.strategies.get(0), this.nbRoundPartie);
			}
			else
			{
				if(indexStr1 == (indexStr2+1))
					p = new Partie(str1, null, this.nbRoundPartie);
				else
					p = new Partie(str1, this.strategies.get(indexStr2+1), this.nbRoundPartie);
			}
		}
		
		
		this.parties.add(p);
	}
	@Override
	public void start() 
	{
		computeGame();
		int taille = this.parties.size();
		
		System.out.print("           \t|  ");
		for (IStrategy s : this.strategies) 
		{
			String m = s.toString();
			if(s instanceof StratMechant || s instanceof StratGentille)
			{
				System.out.print(m+" importée");
			}
			else
			{
				System.out.print(m+"         ");
			}
			System.out.print("\t|  ");
		}
		System.out.println("TOTAL ");
		System.out.println("-----------------+----------------------+-----------------------+--------------------");
		
		int nbParties = 0;
		List<Integer> scoresStr1= new ArrayList<Integer>();
		for (Partie p : parties) {
			nbParties++;
			
			
			
			
			IStrategy str1  = (IStrategy) p.getScores().keySet().toArray()[0];
			//Strategie str2  = (Strategie) p.getScores().keySet().toArray()[1];
			scoresStr1.add(p.getScores().get(str1));
			
			if(nbParties == this.strategies.size())
			{
				String m = str1.toString();
				if(str1 instanceof StratMechant || str1 instanceof StratGentille)
					System.out.print(m+" importée");
				else
					System.out.print(m+"         ");
				System.out.print("|");
				int total = 0;
				for (Integer integer : scoresStr1) 
				{
					total += integer;
					System.out.print("\t"+integer+"         \t|");
					
				}
				System.out.println("\t"+total);
				nbParties = 0;
				scoresStr1 = new ArrayList<Integer>();
				System.out.println("-----------------+----------------------+-----------------------+--------------------"); 	
			}
		}
	}
	

	
	@Override
	public boolean isFinished() {
		return this.parties.size() ==( this.strategies.size()*this.strategies.size() );
	}

	@Override
	public void compute() {
		// TODO Auto-generated method stub
		
	}
}
