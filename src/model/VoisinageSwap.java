package model;

import java.util.ArrayList;

public class VoisinageSwap implements Voisinage {
	
	protected int i = 0;
	protected int j = 1;
	protected ArrayList<Integer> solution ;
	protected int tailleSolution ;
	@Override
	public boolean hasnext() {
		return (this.j<this.tailleSolution-1 || this.i<this.tailleSolution-2);
	}

	@Override
	public ArrayList<Integer> next() {
		@SuppressWarnings("unchecked")
		ArrayList<Integer> sortie = (ArrayList<Integer>) this.solution.clone();
		Integer tmp = sortie.get(i);
		sortie.set(i, sortie.get(j));
		sortie.set(j,tmp) ;
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
		// TODO Auto-generated method stub
		this.solution = solutionInitiale ;
		this.tailleSolution = solutionInitiale.size();
		this.i=0;
		this.j=1;
		return this;
	}

}
