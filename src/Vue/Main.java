package Vue;
import java.util.ArrayList;
import java.util.Scanner;

import Model.DonnantDonnant;
import Model.DonnantDonnantDur;
import Model.Mefiante;
import Model.Tournoi;
import Strategies_2_3.Gentille;
import Strategies_2_3.Mechante;
import interfaceEtChoix.IStrategy;
import strategies.StratGentille;
import strategies.StratMechant;


/**
 * 
 * @author Mehdi BODUOUR
 * M1 - 2019/2020 - G2 
 */

public class Main 
{
		
		static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {


		System.out.println("--------------------------------- DILEMME DU PRISONNIER ------------------------------------");		
		
		ArrayList<IStrategy> strategies = new ArrayList<IStrategy>();
		ArrayList<IStrategy> strategies2 = new ArrayList<IStrategy>();
		ArrayList<IStrategy> strategies3 = new ArrayList<IStrategy>();

		//Strat�gies Import�s
		strategies3.add(new StratGentille());
		strategies3.add(new StratMechant());
		//Strat�gies Locales
		strategies.add(new Gentille());
		strategies.add(new Mechante());
		strategies.add(new Mefiante());
		strategies.add(new DonnantDonnantDur());
		strategies.add(new DonnantDonnant());
				
		
		
		int ss ;
		int j=2;
		
			while(j>0)
			{
				System.out.println("\nStrat�gies disponibles");
				System.out.println("-----------------------");
				int i = 0 ;
				System.out.println(">Strat�gies Locales : ");
				for(IStrategy s : strategies)
				{
					i++;
					String m = i+" - "+s.toString();
					System.out.println(m);
				}
				System.out.println("\n>Strat�gies Import�es : ");
				for(IStrategy s : strategies3)
				{
					i++;
					String m = i+" - "+s.toString();
					System.out.println(m+" import�e");
				}
				
				
				System.out.println("\nVeuillez choisir une strategies :");
				ss = sc.nextInt();
				
				IStrategy selectedStrategy ;
				if(ss>strategies.size())
				{
					if(ss<=strategies.size() + strategies3.size())
					{
						selectedStrategy = strategies3.get(ss-strategies.size()-1);
						//System.out.println(selectedStrategy);
						if(selectedStrategy.toString().equals("Gentille") || selectedStrategy.toString().equals("Mechante"))
						{
							strategies2.add(selectedStrategy);
							strategies3.remove(ss-strategies.size()-1);
							j--;
							continue;
						}
						//Dead Code 
						if(!selectedStrategy.toString().equals("Gentille") && !selectedStrategy.toString().equals("Mechante"))
						{
							System.err.println("Le comportement des cette strat�gie n'est pas impl�ment�.");
							continue;
						}

					}
					else
					{
						System.err.println("Veillez choisir un numero valide !");
						continue;
					}
					
				}
				if(!strategies.get(ss-1).toString().equals("Gentille") && !strategies.get(ss-1).toString().equals("Mechante"))
				{
					System.err.println("Le comportement des cette strat�gie n'est pas impl�ment�.");
					continue;
				}
				if(strategies.get(ss-1).toString().equals("Gentille"))
				{
					strategies2.add(strategies.get(ss-1));
					strategies.remove(ss-1);
					j--;
					continue;
				}
				if(strategies.get(ss-1).toString().equals("Mechante"))
				{
					strategies2.add(strategies.get(ss-1));
					strategies.remove(ss-1);
					j--;
					continue;
				}
				
			}
			System.out.println("\nParticipantes au tournoi sont :");
			System.out.println("-----------------------");
			int i = 0 ;
			for(IStrategy s : strategies2)
			{
				i++;
				String m = i+" - "+s.toString();
				if(s instanceof StratMechant || s instanceof StratGentille)
					System.out.println(m+" import�e");
				else
					System.out.println(m);
				
			}
		System.out.print("\nVeuillez saisir le nombre de tours : ");
		int tours= sc.nextInt();
		
		Tournoi t = new Tournoi(strategies2, tours);
		
		t.start();
		
	}
	}

