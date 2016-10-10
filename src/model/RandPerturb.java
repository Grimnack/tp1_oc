package model;

import java.util.ArrayList;
import java.util.Collections;

public class RandPerturb implements Perturbateur {

	@Override
	public ArrayList<Integer> perturbe(ArrayList<Integer> optimaLocale) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer> perturb = (ArrayList<Integer>) optimaLocale.clone() ;
		Collections.shuffle(perturb);
		return perturb ;
	}

}
