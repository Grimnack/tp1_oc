package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genetique {
	protected SMTWTP probleme ;
	protected boolean first ;
	protected ArrayList<ArrayList<Integer>> population = new ArrayList<ArrayList<Integer>>() ;
	protected int t = 0;
	protected int taillePop ;
	protected int tailleTournoi;
	protected int[][] couples ;
	
	public Genetique(SMTWTP probleme, boolean first, int taillePop, int tailleTournoi){
		this.probleme = probleme ;
		this.first = first ;
		this.taillePop = taillePop ;
		this.tailleTournoi = tailleTournoi;
		this.couples = this.generateCouples();
	}
	
	/**
	 * Tout simplement la génération des premiers individus
	 */
	public void generate() {
		/*
		 * On initialise notre population par des solutions aléatoire
		 */
		for (int i = 0; i < this.taillePop; i++) {
			this.population.add(this.probleme.genereSolutionAleatoire());
		}
	}
	
	/**
	 * 
	 * @param population
	 * @return la liste des scores de chaque individu
	 */
	public ArrayList<Integer> evaluate(ArrayList<ArrayList<Integer>> population) {
		ArrayList<Integer> resultat = new ArrayList<Integer>();
		for (ArrayList<Integer> individu : population) {
			resultat.add(this.probleme.eval(individu)) ;
		}
		return resultat;
	}
	
	/**
	 * donne tous les couples possibles en fonction de la taille d'un tournoi
	 * @return un tableau de couples d'entier
	 */
	private int[][] generateCouples(){
		int[][] couples = new int[(this.tailleTournoi*(this.tailleTournoi - 1))/2][2] ;
		int cpt = 0;
		for (int i = 0; i < tailleTournoi - 1; i++) {
			for (int j = i + 1; j < tailleTournoi; j++) {
				int[] couple = new int[2];
				couple[0] = i;
				couple[1] = j;
				couples[cpt] = couple ;
				cpt++;
			}
			
		}
		return couples;
	}
	
	public ArrayList<ArrayList<Integer>> reproduction(ArrayList<ArrayList<Integer>> tournoi){
		ArrayList<ArrayList<Integer>> generationPlusPlus = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < couples.length; i++) {
			int[] couple = couples[i];
			generationPlusPlus.add(this.reproductionSimple(tournoi.get(couple[0]),tournoi.get(couple[1])));
		}
		return generationPlusPlus;
	}
	
	/**
	 * 
	 * @param papa
	 * @param maman
	 * @return l enfant du papa et de la maman oui c est beau.
	 */
	public ArrayList<Integer> reproductionSimple(ArrayList<Integer> papa,ArrayList<Integer> maman ){
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
	 * @return Le tournoi de taille nbSelect.
	 */
	public ArrayList<ArrayList<Integer>> selection(int nbSelect,ArrayList<Integer> evaluation) {
		Random rand = new Random() ;
		ArrayList<ArrayList<Integer>> popCopy = (ArrayList<ArrayList<Integer>>) this.population.clone() ;
		ArrayList<Integer> evalCopy = (ArrayList<Integer>) evaluation.clone();
		ArrayList<ArrayList<Integer>> tournament = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < nbSelect; i++) {
			int k = this.randomWeightedChoice(popCopy, evalCopy);
			tournament.add(popCopy.remove(k));
			evalCopy.remove(k);
		}
		return tournament ;
	}
	
	private int randomWeightedChoice(ArrayList<ArrayList<Integer>> population, ArrayList<Integer> evaluation){
		ArrayList<Integer> escalierPoids = new ArrayList<Integer>() ;
		int somme = 0;
		for (Integer integer : evaluation) {
			somme+= integer ;
		}
		escalierPoids.add(1-evaluation.get(0)/somme) ;
		for(int i = 1; i < evaluation.size();i++){
			Integer precedent = escalierPoids.get(i-1);
			escalierPoids.add(precedent+(1-evaluation.get(i)/somme));
		}
		Random random = new Random() ;
		float randomResult = random.nextFloat();
		int k = 0;
		while(true){
			if(randomResult>escalierPoids.get(k)){
				k++;
			}else{
				return k;
			}
		}
	}
	/**
	 * 
	 * @param evaluation
	 * @param newGen
	 * @param newEvaluation
	 */
	public void updatePopulation(ArrayList<Integer> evaluation,ArrayList<ArrayList<Integer>> newGen,ArrayList<Integer> newEvaluation ){
		ArrayList<ArrayList<Integer>> futurGen = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < this.taillePop; i++) {
			if(!(evaluation.isEmpty() || newEvaluation.isEmpty())){
				int indice1 = evaluation.indexOf(Collections.min(evaluation));
				int indice2 = newEvaluation.indexOf(Collections.min(newEvaluation));
				if(evaluation.get(indice1)<newEvaluation.get(indice2)){
					futurGen.add(population.remove(indice1));
					evaluation.remove(indice1);
				}else{
					futurGen.add(newGen.remove(indice2));
					newEvaluation.remove(indice2);
				}
			}else if (evaluation.isEmpty()){
				int indice2 = newEvaluation.indexOf(Collections.min(newEvaluation));
				futurGen.add(newGen.remove(indice2));
				newEvaluation.remove(indice2);
			}else if (newEvaluation.isEmpty()){
				int indice1 = evaluation.indexOf(Collections.min(evaluation));
				futurGen.add(population.remove(indice1));
				evaluation.remove(indice1);
			}
			
		}
		this.population = futurGen;
	}

	
	public ArrayList<Integer> run() {
		this.generate();
		int i = 0;
		while(i<100000){
			ArrayList<Integer> evaluation = this.evaluate(population);
			ArrayList<ArrayList<Integer>> tournoi = this.selection(3, evaluation);
			ArrayList<ArrayList<Integer>> newGen = this.reproduction(tournoi);
			ArrayList<Integer> newEvaluation = this.evaluate(newGen);
			this.updatePopulation(evaluation, newGen, newEvaluation);
			i++;
		}
		return population.get(0);
	}
}
