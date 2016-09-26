package model;

import java.util.ArrayList;

public class VoisinageInsertionGauche implements Voisinage {
	protected ArrayList<Integer> solution ;
	protected int i=0;
	protected int j=1;

	@Override
	public boolean hasnext() {
		return this.i + 1 < this.j || this.j + 1 < this.solution.size();
	}

	@Override
	public ArrayList<Integer> next() {
		ArrayList<Integer> sortie = (ArrayList<Integer>) this.solution.clone();
		Integer pick = sortie.remove(this.i) ;
		sortie.add(this.j, pick);
		if( this.i + 1 < this.j) { 
			this.i++;
		}else{
			this.i = 0;
			this.j++ ;
		}
		return sortie;
	}

	@Override
	public Voisinage init(ArrayList<Integer> solutionInitiale) {
		this.solution = solutionInitiale ;
		this.i = 0;
		this.j = 1;
		return this;

	}

}
