package model;

import java.util.ArrayList;

public class ILS {
	protected SMTWTP probleme ;
	protected boolean first ;
	protected Perturbateur pertubateur ;
	protected Accepteur accepteur;
	protected LocalSearch ls;
	
	public ILS(SMTWTP probleme,boolean first,Perturbateur perturbateur, Accepteur accepteur, LocalSearch ls){
		this.first = first ;
		this.probleme = probleme;
		this.pertubateur = perturbateur ;
		this.accepteur = accepteur;
		this.ls = ls;
	}
	
	public ArrayList<Integer> run() {
		// La solution initiale est générée à la création de notre ls
		ArrayList<Integer> optimaLocale = this.ls.run() ;
		boolean accept = true ;
		while(accept){
			ArrayList<Integer> move = this.pertubateur.perturbe(optimaLocale) ;
			this.ls.setSolutionInitiale(move);
			ArrayList<Integer> optimaMoved = this.ls.run() ;
			if (this.accepteur.accepte(optimaMoved, optimaLocale, this.probleme)) {
//				System.out.println("here");
				optimaLocale = optimaMoved ;
			}else{
				accept = false ;
			}
		}
		return optimaLocale ;
	}
}
