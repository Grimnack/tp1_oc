package model;

import java.util.ArrayList;
import java.util.Random;

public class RandSwap implements Perturbateur {

	@Override
	public ArrayList<Integer> perturbe(ArrayList<Integer> optimaLocale) {
		Random rand = new Random();
		int i = rand.nextInt(optimaLocale.size()-1);
		int j = rand.nextInt(optimaLocale.size() - i) + i;
		@SuppressWarnings("unchecked")
		ArrayList<Integer> perturb = (ArrayList<Integer>) optimaLocale.clone();
		Integer tmp = perturb.get(i);
		perturb.set(i, perturb.get(j));
		perturb.set(j,tmp) ;
		return perturb;
	}

}
