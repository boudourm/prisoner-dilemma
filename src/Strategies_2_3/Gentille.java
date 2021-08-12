package Strategies_2_3;

import interfaceEtChoix.Choix;
import interfaceEtChoix.IStrategy;

public class Gentille implements IStrategy
{

	@Override
	public String toString() {
		return "Gentille";
	}

	//Appel avec null en paramettre
	@Override
	public Choix jouerCoup(Choix choix) {
		// TODO Auto-generated method stub
		return Choix.values()[0];
	}


	
}
