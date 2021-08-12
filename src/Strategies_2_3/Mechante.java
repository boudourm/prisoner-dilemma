package Strategies_2_3;

import interfaceEtChoix.Choix;
import interfaceEtChoix.IStrategy;

public class Mechante implements IStrategy
{	


	//Appel avec null en paramettre
		@Override
		public Choix jouerCoup(Choix choix) {
			// TODO Auto-generated method stub
			return Choix.values()[1];
		}

	@Override
	public String toString() {
		return "Mechante";
	}



}
