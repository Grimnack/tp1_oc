package model;

import java.util.ArrayList;

public class VND {
	protected SMTWTP probleme;
	protected ArrayList<Voisinage> lesVoisinages;
	protected boolean first;
	protected ArrayList<Integer> solutionActuelle ;
	protected int evalActuelle ;
	
	
	public VND(SMTWTP instance,ArrayList<Voisinage> lesVoisinages,boolean first,String init){
		this.probleme = instance ;
		this.lesVoisinages = lesVoisinages ;
		this.first = first ;
		if(init == "rnd"){
			this.solutionActuelle = this.probleme.genereSolutionAleatoire() ;
		}else if (init=="mdd"){
			this.solutionActuelle = this.probleme.modifiedDueDate() ;
		}else{
			this.solutionActuelle = this.probleme.earliestDueDate() ;
		}
		this.evalActuelle = this.probleme.eval(this.solutionActuelle);
	}
	
	public boolean choose(int indexVoisinage){
		boolean trouve = false;
		Voisinage voisinage = this.lesVoisinages.get(indexVoisinage);
		while(voisinage.hasnext()){
			ArrayList<Integer> voisin = voisinage.next() ;
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
	
	public ArrayList<Integer> run(){
		int i = 0 ;
		boolean bloqued = false ;
		while(!bloqued) {
			this.lesVoisinages.get(i).init(this.solutionActuelle);
			boolean ameliore = this.choose(i);
			if(ameliore){
				i = 0 ;
			}else{
				i++;
				if(i==this.lesVoisinages.size()){
					bloqued = true ;
				}
			}
		}
		return this.solutionActuelle;
		
	}
}
