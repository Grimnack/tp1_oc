package model;

import java.util.ArrayList;
import java.util.Random;

public class TwoOptPerturb implements Perturbateur{

	private int randInt(int min, int max) {

	    // NOTE: This will (intentionally) not run as written so that folks
	    // copy-pasting have to think about how to initialize their
	    // Random instance.  Initialization of the Random instance is outside
	    // the main scope of the question, but some decent options are to have
	    // a field that is initialized once and then re-used as needed or to
	    // use ThreadLocalRandom (if using at least Java 1.7).
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	@Override
	public ArrayList<Integer> perturbe(ArrayList<Integer> optimaLocale) {
		ArrayList<Integer> perturb = new ArrayList<Integer>() ;
		int i = this.randInt(0, optimaLocale.size()-2);
		int j = this.randInt(i, optimaLocale.size()-1);
		for (int k = 0; k < i; k++) {
			perturb.add(optimaLocale.get(k));
		}
		for (int k = j; k>= i;k--){
			perturb.add(optimaLocale.get(k));
		}
		for (int k = j + 1; k<optimaLocale.size();k++){
			perturb.add(optimaLocale.get(k)) ;
		}
		return perturb;
	}

}
