package statistiques;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Accepteur;
import model.HillClimbing;
import model.ILS;
import model.LocalSearch;
import model.Main;
import model.OnlyBest;
import model.Perturbateur;
import model.PipedVND;
import model.RandSwap;
import model.SMTWTP;
import model.TwoOptPerturb;
import model.VND;
import model.Voisinage;
import model.VoisinageContigu;
import model.VoisinageInsertionGauche;
import model.VoisinageSwap;

public class StatGen {

	private ArrayList<String> starters = new ArrayList<String>() ;
	private ArrayList<String> select = new ArrayList<String>() ;
	private ArrayList<String> lesVoisinages = new ArrayList<String>() ;
	private Voisinage voisinage ;
	private boolean first ;
	
	public StatGen(){
		this.starters.add("rnd");
		this.starters.add("edd");
		this.starters.add("mdd");
		this.select.add("first");
		this.select.add("best") ;
		this.lesVoisinages.add("simple");
		this.lesVoisinages.add("swap");
		this.lesVoisinages.add("insert");
		
		
	}
	
	private double mean(ArrayList<Integer> marks) {
		Integer sum = 0;
		  if(!marks.isEmpty()) {
		    for (Integer mark : marks) {
		        sum += mark;
		    }
		    return sum.doubleValue() / marks.size();
		  }
		  return sum;
	}
	
	/**
	 * genere un fichier une ligne = une instance
	 * la ligne = moyenne(30 eval); moyenne(temps cpu)
	 */
	public void genereStatRandSol() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		try {
			File f = new File("random.stat");
			FileWriter fw = new FileWriter(f) ;
			for(SMTWTP instance : main.getInstances()) {
				ArrayList<Integer> lesEvals = new ArrayList<Integer>() ;
				ArrayList<Integer> lesTemps = new ArrayList<Integer>() ;
				for (int i = 0; i < 30; i++) {
					long startTime = System.currentTimeMillis() ;
					ArrayList<Integer> solution = instance.genereSolutionAleatoire() ;
					long estimatedTime = System.currentTimeMillis() - startTime ;
					lesEvals.add(instance.eval(solution)) ;
					lesTemps.add((int) estimatedTime) ;
				}
				fw.write(String.valueOf(this.mean(lesEvals)) + ";" + String.valueOf(this.mean(lesTemps))+"\n");
			}
			fw.close() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void genereStatEDDSol() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		try {
			File f = new File("edd.stat");
			FileWriter fw = new FileWriter(f) ;
			for(SMTWTP instance : main.getInstances()) {
				ArrayList<Integer> lesEvals = new ArrayList<Integer>() ;
				ArrayList<Integer> lesTemps = new ArrayList<Integer>() ;
				for (int i = 0; i < 30; i++) {
					long startTime = System.currentTimeMillis() ;
					ArrayList<Integer> solution = instance.earliestDueDate() ;
					long estimatedTime = System.currentTimeMillis() - startTime ;
					lesEvals.add(instance.eval(solution)) ;
					lesTemps.add((int) estimatedTime) ;
				}
				fw.write(String.valueOf(this.mean(lesEvals)) + ";" + String.valueOf(this.mean(lesTemps))+"\n");
			}
			fw.close() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void genereStatMDDSol() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		try {
			File f = new File("mdd.stat");
			FileWriter fw = new FileWriter(f) ;
			for(SMTWTP instance : main.getInstances()) {
				ArrayList<Integer> lesEvals = new ArrayList<Integer>() ;
				ArrayList<Integer> lesTemps = new ArrayList<Integer>() ;
				for (int i = 0; i < 30; i++) {
					long startTime = System.currentTimeMillis() ;
					ArrayList<Integer> solution = instance.modifiedDueDate() ;
					long estimatedTime = System.currentTimeMillis() - startTime ;
					lesEvals.add(instance.eval(solution)) ;
					lesTemps.add((int) estimatedTime) ;
				}
				fw.write(String.valueOf(this.mean(lesEvals)) + ";" + String.valueOf(this.mean(lesTemps))+"\n");
			}
			fw.close() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void genereStatHillClimbing() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		try {
			for (String selecteur : this.select) {
				if(selecteur == "first") {
					this.first = true ;
				}else{
					this.first = false ;
				}
				for(String voisin : this.lesVoisinages){
					if (voisin == "simple") {
						this.voisinage = new VoisinageContigu() ;
					}else if (voisin == "insert"){
						this.voisinage = new VoisinageInsertionGauche() ;
					}else {
						this.voisinage = new VoisinageSwap();
					}
					for(String init : this.starters){
						File f = new File("hc_"+selecteur+"_"+voisin+"_"+init+".stat");
						FileWriter fw = new FileWriter(f) ;
						for(SMTWTP instance : main.getInstances()) {
							ArrayList<Integer> lesEvals = new ArrayList<Integer>() ;
							ArrayList<Integer> lesTemps = new ArrayList<Integer>() ;
							for(int i = 0; i < 1;i++){
								long startTime = System.currentTimeMillis() ;
								HillClimbing hc = new HillClimbing(instance, first, voisinage, init);
								ArrayList<Integer> solution = hc.run() ;
								long estimatedTime = System.currentTimeMillis() - startTime ;
								lesEvals.add(instance.eval(solution)) ;
								lesTemps.add((int) estimatedTime) ;
							}
							fw.write(String.valueOf(this.mean(lesEvals)) + ";" + String.valueOf(this.mean(lesTemps))+"\n");
						}
						fw.close();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void genereStatVND() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		ArrayList<Voisinage> voisinagesVnd; 
		String voisin ;
		try {
			for (String selecteur : this.select) {
				if(selecteur == "first") {
					this.first = true ;
				}else{
					this.first = false ;
				}
				for(int k= 0; k < 2; k++){
					if (k == 0) {
						voisin = "ech-ins-swap" ;
						voisinagesVnd = new ArrayList<Voisinage>() ;
						voisinagesVnd.add(new VoisinageContigu()) ;
						voisinagesVnd.add(new VoisinageInsertionGauche()) ;
						voisinagesVnd.add(new VoisinageSwap()) ;
					}else{
						voisin = "ech-swap-ins" ;
						voisinagesVnd = new ArrayList<Voisinage>() ;
						voisinagesVnd.add(new VoisinageContigu()) ;
						voisinagesVnd.add(new VoisinageSwap()) ;
						voisinagesVnd.add(new VoisinageInsertionGauche()) ;
					}
					for(String init : this.starters){
						File f = new File("vnd_"+selecteur+"_"+voisin+"_"+init+".stat");
						FileWriter fw = new FileWriter(f) ;
						for(SMTWTP instance : main.getInstances()) {
							ArrayList<Integer> lesEvals = new ArrayList<Integer>() ;
							ArrayList<Integer> lesTemps = new ArrayList<Integer>() ;
							for(int i = 0; i < 1;i++){
								long startTime = System.currentTimeMillis() ;
								VND vnd = new VND(instance, voisinagesVnd, first, init) ;
								ArrayList<Integer> solution = vnd.run() ;
								long estimatedTime = System.currentTimeMillis() - startTime ;
								lesEvals.add(instance.eval(solution)) ;
								lesTemps.add((int) estimatedTime) ;
							}
							fw.write(String.valueOf(this.mean(lesEvals)) + ";" + String.valueOf(this.mean(lesTemps))+"\n");
						}
						fw.close();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void genereStatPipedVND() {
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		ArrayList<Voisinage> voisinagesVnd; 
		String voisin ;
		try {
			for (String selecteur : this.select) {
				if(selecteur == "first") {
					this.first = true ;
				}else{
					this.first = false ;
				}
				for(int k= 0; k < 2; k++){
					if (k == 0) {
						voisin = "ech-ins-swap" ;
						voisinagesVnd = new ArrayList<Voisinage>() ;
						voisinagesVnd.add(new VoisinageContigu()) ;
						voisinagesVnd.add(new VoisinageInsertionGauche()) ;
						voisinagesVnd.add(new VoisinageSwap()) ;
					}else{
						voisin = "ech-swap-ins" ;
						voisinagesVnd = new ArrayList<Voisinage>() ;
						voisinagesVnd.add(new VoisinageContigu()) ;
						voisinagesVnd.add(new VoisinageSwap()) ;
						voisinagesVnd.add(new VoisinageInsertionGauche()) ;
					}
					for(String init : this.starters){
						File f = new File("pvnd_"+selecteur+"_"+voisin+"_"+init+".stat");
						FileWriter fw = new FileWriter(f) ;
						for(SMTWTP instance : main.getInstances()) {
							ArrayList<Integer> lesEvals = new ArrayList<Integer>() ;
							ArrayList<Integer> lesTemps = new ArrayList<Integer>() ;
							for(int i = 0; i < 1;i++){
								long startTime = System.currentTimeMillis() ;
								PipedVND pvnd = new PipedVND(instance, voisinagesVnd, first, init) ;
								ArrayList<Integer> solution = pvnd.run() ;
								long estimatedTime = System.currentTimeMillis() - startTime ;
								lesEvals.add(instance.eval(solution)) ;
								lesTemps.add((int) estimatedTime) ;
							}
							fw.write(String.valueOf(this.mean(lesEvals)) + ";" + String.valueOf(this.mean(lesTemps))+"\n");
						}
						fw.close();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void genereStatILS(){
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		ArrayList<Voisinage> lesVoisinages = new ArrayList<Voisinage>() ;
		lesVoisinages.add(new VoisinageContigu());
		lesVoisinages.add(new VoisinageInsertionGauche()) ;
		lesVoisinages.add(new VoisinageSwap()) ;
		String voisin = "ech-ins-swap" ;
		Accepteur accepteur = new OnlyBest() ;
		Perturbateur perturbateur = new RandSwap();
		try {
			for (String selecteur : this.select) {
				if(selecteur == "first") {
					this.first = true ;
				}else{
					this.first = false ;
				}
				for(String init : this.starters){
					File f = new File("ilsVND_"+selecteur+"_"+voisin+"_"+init+".stat");
					FileWriter fw = new FileWriter(f) ;
					for(SMTWTP instance : main.getInstances()) {
						ArrayList<Integer> lesEvals = new ArrayList<Integer>() ;
						ArrayList<Integer> lesTemps = new ArrayList<Integer>() ;
						for(int i = 0; i < 1;i++){
							long startTime = System.currentTimeMillis() ;
							LocalSearch ls = new VND(instance, lesVoisinages, first, init) ;
							ILS ils = new ILS(instance, true, perturbateur, accepteur, ls);
							ArrayList<Integer> solution = ils.run();
							long estimatedTime = System.currentTimeMillis() - startTime ;
							lesEvals.add(instance.eval(solution)) ;
							lesTemps.add((int) estimatedTime) ;
						}
						fw.write(String.valueOf(this.mean(lesEvals)) + ";" + String.valueOf(this.mean(lesTemps))+"\n");
					}
					fw.close();
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void genereStatILS2opt(){
		Main main = new Main() ;
		main.lecture("src/test/wt100.txt") ;
		ArrayList<Voisinage> lesVoisinages = new ArrayList<Voisinage>() ;
		lesVoisinages.add(new VoisinageContigu());
		lesVoisinages.add(new VoisinageInsertionGauche()) ;
		lesVoisinages.add(new VoisinageSwap()) ;
		String voisin = "ech-ins-swap" ;
		Accepteur accepteur = new OnlyBest() ;
		Perturbateur perturbateur = new TwoOptPerturb();
		try {
			for (String selecteur : this.select) {
				if(selecteur == "first") {
					this.first = true ;
				}else{
					this.first = false ;
				}
				for(String init : this.starters){
					File f = new File("ils2optVND_"+selecteur+"_"+voisin+"_"+init+".stat");
					FileWriter fw = new FileWriter(f) ;
					for(SMTWTP instance : main.getInstances()) {
						ArrayList<Integer> lesEvals = new ArrayList<Integer>() ;
						ArrayList<Integer> lesTemps = new ArrayList<Integer>() ;
						for(int i = 0; i < 1;i++){
							long startTime = System.currentTimeMillis() ;
							LocalSearch ls = new VND(instance, lesVoisinages, first, init) ;
							ILS ils = new ILS(instance, true, perturbateur, accepteur, ls);
							ArrayList<Integer> solution = ils.run();
							long estimatedTime = System.currentTimeMillis() - startTime ;
							lesEvals.add(instance.eval(solution)) ;
							lesTemps.add((int) estimatedTime) ;
						}
						fw.write(String.valueOf(this.mean(lesEvals)) + ";" + String.valueOf(this.mean(lesTemps))+"\n");
					}
					fw.close();
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static void main(String[] args){
		StatGen main = new StatGen();
//		main.genereStatRandSol() ;
//		main.genereStatEDDSol() ;
//		main.genereStatMDDSol() ;
//		main.genereStatHillClimbing();
//		main.genereStatVND() ;
//		main.genereStatPipedVND();
//		main.genereStatILS();
		main.genereStatILS2opt();
		System.out.println("fini");
	}
	
}
