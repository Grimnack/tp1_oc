package test;

import model.Main;
import model.SMTWTP;

import org.junit.Test;

public class SMTWTPTEst {

	@Test
	public void genereSolutionAleatoireTest() {
		SMTWTP instance = new SMTWTP(10, null, null, null) ;
		for (int i = 0; i < 10; i++) {
			System.out.println(instance.genereSolutionAleatoire());
			
		}
	}
	
	@Test
	public void earliestDueDateTest() {
		int[] d = new int[4] ;
		d[0] = 6 ;
		d[1] = 4 ;
		d[2] = 2;
		d[3] = 4 ;
		SMTWTP instance = new SMTWTP(4, null, d, null) ;
		for (int i = 0; i < 10; i++) {
			System.out.println(instance.earliestDueDate());
		}
	}
	
	@Test
	public void evalTest() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		for (SMTWTP instance : main.getInstances()) {
			System.out.println(instance.eval(instance.earliestDueDate()));
		}
	}

}
