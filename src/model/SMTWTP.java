package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class SMTWTP {
	protected int n; //nombre de jobs
	protected int[] p; //temps d execution par job
	protected int[] d; //deadline souhaité par job
	protected int[] w; //poids de chaque job

	public SMTWTP(int n,int[] p, int[] d, int[] w) {
		this.n = n ;
		this.p = p ;
		this.d = d ;
		this.w = w ;
	}

	
	/**
	 * Le temps de completion d'un job j
	 * @param j un job
	 * @param start le temps de départ de la tâche j.
	 * @return son temps de completion en int.
	 */
	public int C(int j,int start){
		return start+this.p[j];
	}
	
	
	public int T(int j,int start) {
		return Math.max(C(j,start)-this.d[j],0) ;
	}
	
	/**
	 * fonction de coût
	 * @param o l'ordre des tâches.
	 * @return le coût de la solution o
	 */
	public int eval(ArrayList<Integer> lesJobs){
		int start = 0 ;
		int cost = 0 ;
		for (int i = 0; i < this.n; i++) {
			int j = lesJobs.get(i);
			cost = cost + (this.w[j]*this.T(j, start)) ;
			start = start + this.p[j] ; 
		}
		
		return cost;
	}


	@Override
	public String toString() {
		return "SMTWTP [n=" + n + ", p=" + Arrays.toString(p) + ", d="
				+ Arrays.toString(d) + ", w=" + Arrays.toString(w) + "]";
	}
	
	/**
	 * Genere une solution aléatoire du problème.
	 * @return la solution aléatoire sous forme d'une liste d'Integer (les permutations) 
	 */
	public ArrayList<Integer> genereSolutionAleatoire() {
		ArrayList<Integer> solutionAlea = new ArrayList<Integer>();
		for(int i = 0; i<this.n; i++) {
			solutionAlea.add(i) ;
		}
		Collections.shuffle(solutionAlea);
		return solutionAlea ;
		
	}
	
	public int compareTwoJobsDueDate(int indice1, int indice2){
		Random rand = new Random() ;
		if (this.d[indice1]>this.d[indice2]) {
			return 1 ;
		}else if (this.d[indice1]<this.d[indice2]){
			return -1;
		}else{
			if (rand.nextBoolean()) {
				return 1 ;
			} else {
				return -1 ;
			}
		}
	}
	
	
	/**
	 * trie la liste des jobs par ordre croissant de leur dj
	 * gère les égalités de façon aléatoire.
	 * @return
	 */
	public ArrayList<Integer> earliestDueDate(){
		ArrayList<Integer> lesJobs = new ArrayList<Integer>();
		for (int i = 0; i < this.n; i++) {
			lesJobs.add(i) ;
		}
		final int d[] = this.d.clone() ;
		
		
		Collections.sort(lesJobs, new Comparator<Integer>(){
			@Override
			public int compare(Integer indice1, Integer indice2){
				Random rand = new Random() ;
				if (d[indice1]>d[indice2]) {
					return 1 ;
				}else if (d[indice1]<d[indice2]){
					return -1;
				}else{
					if (rand.nextBoolean()) {
						return 1 ;
					} else {
						return -1 ;
					}
				}
			}
		});
		
		
		return lesJobs ;
	}
	
	public ArrayList<Integer> modifiedDueDate(){
		ArrayList<Integer> lesJobs = new ArrayList<Integer>();
		ArrayList<Integer> resultat = new ArrayList<Integer>() ;
		for (int i = 0; i < this.n; i++) {
			lesJobs.add(i) ;
		}
		int C = 0;
		final int[] p = this.p.clone();
		final int[] d = this.d.clone();
		while (!lesJobs.isEmpty()) {
			final int[] mdd = new int[this.n] ;
			for (Integer i : lesJobs) {
				mdd[i] = Math.max(C+p[i],d[i]) ;
			}
			Collections.sort(lesJobs, new Comparator<Integer>(){
				@Override
				public int compare(Integer indice1,Integer indice2){
					Random rand = new Random() ;
					if(mdd[indice1]<mdd[indice2]){
						return -1;
					}else if (mdd[indice1]>mdd[indice2]){
						return 1 ;
					}else{
						if(rand.nextBoolean()){
							return -1;
						}else{
							return 1 ;
						}
					}
				}
			});
			Integer meilleur = lesJobs.remove(0) ;
			resultat.add(meilleur);
			C = C + p[meilleur] ;
			
		}
		
		
		return lesJobs ;
	}
	
}
