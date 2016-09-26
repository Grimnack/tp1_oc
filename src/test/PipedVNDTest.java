package test;

import java.util.ArrayList;

import model.Main;
import model.PipedVND;
import model.SMTWTP;
import model.Voisinage;
import model.VoisinageContigu;
import model.VoisinageInsertionGauche;
import model.VoisinageSwap;

import org.junit.Test;

public class PipedVNDTest {

	@Test
	public void test() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		ArrayList<Voisinage> lesVoisinages = new ArrayList<Voisinage>();
		lesVoisinages.add(new VoisinageContigu());
		lesVoisinages.add(new VoisinageInsertionGauche()) ;
		lesVoisinages.add(new VoisinageSwap()) ;
		for (SMTWTP instance : main.getInstances()) {
			PipedVND pvnd = new PipedVND(instance, lesVoisinages, true, "rnd");
			ArrayList<Integer> lesJobs = pvnd.run();
			System.out.println(instance.eval(lesJobs));
		}
	}

}
