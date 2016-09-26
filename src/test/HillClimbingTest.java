package test;

import java.util.ArrayList;

import model.HillClimbing;
import model.Main;
import model.SMTWTP;
import model.VoisinageContigu;
import model.VoisinageInsertionGauche;

import org.junit.Test;

public class HillClimbingTest {

	@Test
	public void runTest() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		for (SMTWTP instance : main.getInstances()) {
			HillClimbing hc = new HillClimbing(instance, true, new VoisinageInsertionGauche(), "mdd");
//			System.out.println(lesJobs);
			ArrayList<Integer> lesJobs = hc.run();
			System.out.println(instance.eval(lesJobs));
		}
	};
}
