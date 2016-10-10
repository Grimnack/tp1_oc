package model;

import java.util.ArrayList;

public interface LocalSearch {
	public ArrayList<Integer> run();
	public void setSolutionInitiale(ArrayList<Integer> solutionInitiale);
}
