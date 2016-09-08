package test;

import model.Main;

import org.junit.Test;

public class MainTest {

	@Test
	public void lectureTest() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		System.out.println(main.toString());
	}

}
