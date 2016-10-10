package model;

import java.util.ArrayList;

public class OnlyBest implements Accepteur{

	@Override
	public boolean accepte(ArrayList<Integer> optimaLocaleActuelle,
			ArrayList<Integer> optimaLocalePrecedent, SMTWTP instance) {
		return instance.eval(optimaLocaleActuelle) < instance.eval(optimaLocalePrecedent);
	}
	

}
