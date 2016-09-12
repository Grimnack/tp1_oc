package model;

import java.util.ArrayList;

public class VoisinageInsertion implements Voisinage {
	protected ArrayList<Integer> solution ;

	@Override
	public boolean hasnext() {
		// TODO Auto-generated method stub
		return false;
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
		return this;

	}

}
