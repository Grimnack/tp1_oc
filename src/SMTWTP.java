
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
	public int eval(Ordonnancement o){
		int start = 0 ;
		int cost = 0 ;
		for (int i = 0; i < this.n; i++) {
			int j = o.valueAt(i) ;
			cost = cost + (this.w[j]*this.T(j, start)) ;
			start = start + this.p[j] ; // calculé 2 fois peut être sauvegardé
		}
		
		return cost;
	}
	
	
	
	
	
	
}
