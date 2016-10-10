package test;

import java.util.ArrayList;

import model.Accepteur;
import model.ILS;
import model.LocalSearch;
import model.Main;
import model.OnlyBest;
import model.Perturbateur;
import model.RandSwap;
import model.SMTWTP;
import model.VND;
import model.Voisinage;
import model.VoisinageContigu;
import model.VoisinageInsertionGauche;
import model.VoisinageSwap;

import org.junit.Test;

public class ILSTest {

	@Test
	public void test() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		for (SMTWTP instance : main.getInstances()) {
			Accepteur accepteur = new OnlyBest() ;
//			Perturbateur perturbateur = new RandPerturb() ;
			Perturbateur perturbateur = new RandSwap();
//			LocalSearch ls = new HillClimbing(instance, true, new VoisinageSwap(), "edd") ;
			ArrayList<Voisinage> lesVoisinages = new ArrayList<Voisinage>() ;
			lesVoisinages.add(new VoisinageContigu());
			lesVoisinages.add(new VoisinageInsertionGauche()) ;
			lesVoisinages.add(new VoisinageSwap()) ;
			LocalSearch ls = new VND(instance, lesVoisinages, true, "edd");
			ILS ils = new ILS(instance, true, perturbateur, accepteur, ls);
			System.out.println(instance.eval(ils.run()));
		
		}
		
		
	}

}
