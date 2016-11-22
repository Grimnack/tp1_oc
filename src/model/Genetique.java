package model;

import java.util.ArrayList;
import java.util.Random;

public class Genetique {
	protected SMTWTP probleme ;
	protected boolean first ;
	protected ArrayList<ArrayList<Integer>> population = new ArrayList<ArrayList<Integer>>() ;
	protected int t = 0;
	protected int taillePop ;
	
	public Genetique(SMTWTP probleme, boolean first, int taillePop){
		this.probleme = probleme ;
		this.first = first ;
		this.taillePop = taillePop ;
	}
	
	public void generate() {
		/*
		 * On initialise notre population par des solutions aléatoire
		 */
		for (int i = 0; i < this.taillePop; i++) {
			this.population.add(this.probleme.genereSolutionAleatoire());
		}
	}
	
	public ArrayList<Integer> evaluate(ArrayList<ArrayList<Integer>> population) {
		ArrayList<Integer> resultat = new ArrayList<Integer>();
		for (ArrayList<Integer> individu : population) {
			resultat.add(this.probleme.eval(individu)) ;
		}
		return resultat;
	}
	
	public ArrayList<Integer> reproduction(ArrayList<Integer> papa,ArrayList<Integer> maman ){
		ArrayList<Integer> enfant = new ArrayList<Integer>() ;
		Random rn = new Random() ;
		int i = rn.nextInt(papa.size()-1) ;
		int j = rn.nextInt(papa.size() - i) + i ;
		while(i<=j){
			enfant.add(papa.get(i)) ;
			i ++ ;
		}
		for (Integer element : maman) {
			if(!enfant.contains(element)){
				enfant.add(element) ;
			}
		}
		return enfant;
		
	}
	
	/**
	 * doit choisir les parents de la prochaine génération
	 * choit pondéré en fonction de l evaluation.
	 * @return Les parents de la prochaine génération.
	 */
	public ArrayList<ArrayList<Integer>> selection(int nbSelect) {
		Random rand = new Random() ;
		ArrayList<ArrayList<Integer>> popCopy = (ArrayList<ArrayList<Integer>>) this.population.clone() ;
		for (int i = 0; i < nbSelect; i++) {
			ArrayList<ArrayList<Integer>> tmp = (ArrayList<ArrayList<Integer>>) popCopy.clone() ;
			ArrayList<ArrayList<Integer>> tournament = new ArrayList<ArrayList<Integer>>();
			
			for (int k = 0; k < 3 ; k ++) {
				int index = rand.nextInt(popCopy.size()) ;
				tournament.add(tmp.remove(index)) ;
			}
		}
		return null ;
	}
	
	public ArrayList<Integer> run() {
		return null ;
	}
}
