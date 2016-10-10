package model;

import java.util.ArrayList;

public class HillClimbing implements LocalSearch{
	protected SMTWTP probleme ;
	protected boolean first ;
	protected Voisinage voisinage;
	protected ArrayList<Integer> solutionActuelle ;
	protected int evalActuelle ;
	
	public HillClimbing(SMTWTP probleme,boolean first,Voisinage voisinage,String init) {
		this.probleme = probleme ;
		this.first = first ; // best == !first
		if(init == "rnd"){
			this.solutionActuelle = this.probleme.genereSolutionAleatoire() ;
		}else if (init=="mdd"){
			this.solutionActuelle = this.probleme.modifiedDueDate() ;
		}else{
			this.solutionActuelle = this.probleme.earliestDueDate() ;
		}
		this.voisinage = voisinage.init(this.solutionActuelle) ;
		this.evalActuelle = this.probleme.eval(this.solutionActuelle);
	}
	
	
	public ArrayList<Integer> run(){
		boolean peutAvancer = true ;
		while(peutAvancer){
			peutAvancer = this.choose();
		}
		return this.solutionActuelle;
	}
	
	
	/**
	 * choisit une solution par rapport aux attributs first/best et au voisinnage
	 * @return true si on trouve un meilleur voisin false sinon
	 */
	public boolean choose(){
		boolean trouve = false;
		while(this.voisinage.hasnext()){
			ArrayList<Integer> voisin = this.voisinage.next() ;
			int evalVoisin = this.probleme.eval(voisin) ;
			if(evalVoisin<this.evalActuelle) {
				this.solutionActuelle = voisin ;
				this.evalActuelle = evalVoisin;
				if(first){
					return true;
				}else{ //on est dans le best
					trouve = true;
				}
			}
		}
		return trouve ;
	}


	@Override
	public void setSolutionInitiale(ArrayList<Integer> solutionInitiale) {
		this.solutionActuelle = solutionInitiale ;
		
	}
	
	
	
	
}	
