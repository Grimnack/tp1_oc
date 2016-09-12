package model;

import java.util.ArrayList;

public interface Voisinage {
	public boolean hasnext() ;
	public ArrayList<Integer> next() ;
	public Voisinage init(ArrayList<Integer> solutionInitiale); 
}
