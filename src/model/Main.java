package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	protected List<SMTWTP> lesInstances = new ArrayList<SMTWTP>();

	
	@Override
	public String toString() {
		String chaine = "" ;
		for (SMTWTP instance : lesInstances) {
			chaine = chaine + instance + "\n" ;
		}
		return "Main = \n" + chaine ;
	}

	/**
	 * ajoute le contenu d'une liste à un tableau
	 * @param liste
	 * @param tableau
	 * @param numLigne
	 */
	public void addListToArray(List<Integer> liste, int[] tableau,int numLigne){
		int cpt = 0;
		for (Integer i : liste) {
			tableau[cpt + numLigne*20] = i.intValue() ;
			cpt++;
		}
	}
	
	/**
	 * lit un fichier et ajoute les instances à la liste
	 * @param pathname
	 */
	public void lecture(String pathname){
		try {
			File file = new File(pathname) ;
			if (file.exists()) {
				System.out.println("le fichier existe");
			}
			if (file.canRead()) {
				System.out.println("on peut le lire");
			}
			Scanner scanner = new Scanner(file);
			int[] p = new int[100] ;
			int[] w = new int[100] ;
			int[] d = new int[100] ;
			int cpt = 0;
			while(scanner.hasNextInt()){
				if (cpt==300) {
					cpt = 0 ;
					System.out.println(p);
					System.out.println(d);
					System.out.println(w);
					this.lesInstances.add(new SMTWTP(100, p, d, w));
					p = new int[100] ;
					w = new int[100] ;
					d = new int[100] ;
				}
				System.out.println(cpt);
				if (cpt<100) {
					p[cpt] = scanner.nextInt() ;
				}else if (cpt>=100 && cpt<200){
					w[cpt-100] = scanner.nextInt() ;
				}else{
					d[cpt-200] = scanner.nextInt() ;
				}
				cpt++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
		}
	}
}
