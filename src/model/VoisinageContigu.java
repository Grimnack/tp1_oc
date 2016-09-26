package model;

import java.util.ArrayList;

public class VoisinageContigu implements Voisinage{
	
	protected int i = 0;
	protected ArrayList<Integer> solution ;

	@Override
	public boolean hasnext() {
		return (this.i+1<this.solution.size());
	}

	@Override
	public ArrayList<Integer> next() {
		ArrayList<Integer> sortie = (ArrayList<Integer>) this.solution.clone();
		Integer tmp = sortie.get(i);
		sortie.set(i, sortie.get(i+1)) ;
		sortie.set(i+1, tmp) ;
		this.i++;
		return sortie;
	}

	@Override
	public Voisinage init(ArrayList<Integer> solutionInitiale) {
		this.solution = solutionInitiale ;
		this.i = 0;
		return this;
	}

}
