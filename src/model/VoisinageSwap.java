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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Voisinage init(ArrayList<Integer> solutionInitiale) {
		// TODO Auto-generated method stub
		this.solution = solutionInitiale ;
		this.tailleSolution = solutionInitiale.size();
		return this;
	}

}
