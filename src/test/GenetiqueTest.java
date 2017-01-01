package test;

import java.util.ArrayList;

import model.Genetique;
import model.Main;
import model.SMTWTP;

import org.junit.Test;

public class GenetiqueTest {
	@Test
	public void runTest() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		for (SMTWTP instance : main.getInstances()) {
			Genetique gen = new Genetique(instance, true, 10, 3);
//			System.out.println(lesJobs);
			ArrayList<Integer> lesJobs = gen.run();
			System.out.println(instance.eval(lesJobs));
		}
	};

}
