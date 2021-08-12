package Model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import interfaceEtChoix.IStrategy;


public class Partie implements Process{
	
	
	//Attributs
	private int currentRound;
	private int maxRound;
	private Map<IStrategy , Integer > scores ;
	
	//Associations	
	private List <Round> rounds ;
	private Tournoi tournoi;
	
	//Constructor
	public Partie(IStrategy s1, IStrategy s2 , int maxRound)
	{
		this.maxRound = maxRound;
		this.scores = new LinkedHashMap<IStrategy, Integer>();
		this.scores.put(s1, 0);
		this.scores.put(s2, 0);
		rounds = new ArrayList<Round>();
		
	}
	
	//Guetters & Setters
	public int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public int getMaxRound() {
		return maxRound;
	}

	public void setMaxRound(int maxRound) {
		this.maxRound = maxRound;
	}

	public Map<IStrategy, Integer> getScores() {
		return scores;
	}

	public void setScores(Map<IStrategy, Integer> scores) {
		this.scores = scores;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}
	
	//Methods 
	public void computeGame()
	{
		while(!this.isFinished())
		{
			next();
			
			// <<create>> Round
			
			IStrategy s1  = (IStrategy) this.scores.keySet().toArray()[0];
			IStrategy s2  = (IStrategy) this.scores.keySet().toArray()[1];
			
			Round round = new Round(s1,s2);
			this.rounds.add(round);
			
			//ComputeScore()
			round.computeScore();
			Map<IStrategy,Integer> roundScores = round.getScores();
			
			//Ajouter les scores
			int roundScoreStrategie1 = roundScores.get(s1);
			int roundScoreStrategie2 = roundScores.get(s2);
			
			int upToNowScoreStrategie1 = this.scores.get(s1);
			int upToNowScoreStrategie2 = this.scores.get(s2);

			this.scores.replace(s1, upToNowScoreStrategie1 + roundScoreStrategie1);
			this.scores.replace(s2, upToNowScoreStrategie2 + roundScoreStrategie2);
			
		}
	}
	
	public void next()
	{
		this.setCurrentRound(currentRound+1);
	}

	@Override
	public void start() {
		this.currentRound = 0;
		this.rounds = new ArrayList<Round>();
	}

	@Override
	public boolean isFinished() {
		return this.currentRound==this.maxRound;
		
	}

	@Override
	public void compute() {
		// TODO Auto-generated method stub
		
	}
}
