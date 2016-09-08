package model;

public class Ordonnancement {
	protected int[] lesTaches;
	
	public Ordonnancement(int[] t){
		this.lesTaches = t ;
	}
	
	public int valueAt(int i){
		return this.lesTaches[i] ;
	}
	
}
