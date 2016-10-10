package model;

import java.util.ArrayList;

public interface Accepteur {
	public boolean accepte(ArrayList<Integer> optimaLocaleActuelle, ArrayList<Integer> optimaLocalePrecedent, SMTWTP instance) ;
}
