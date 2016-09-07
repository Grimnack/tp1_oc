import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
	protected List<SMTWTP> lesInstances = new ArrayList<SMTWTP>();

	
	/**
	 * Transforme une chaine de caractère en list d'entiers
	 * @param string de le forme "5  5  6  4" 
	 * @return la list [5,5,6,4]
	 */
	public List<Integer> stringToList(String string) {
		String[] parts = string.split(" ");
		List<Integer> res = new ArrayList<Integer>() ;
		for (int i = 0; i < parts.length; i++) {
			res.add(Integer.parseInt(parts[i])) ;
		}
		return res ;
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
			InputStream ips = new FileInputStream(pathname);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			int numLigne = 0 ;
			int[] p = new int[100] ;
			int[] w = new int[100] ;
			int[] d = new int[100] ;
			while((ligne=br.readLine())!=null){
				if(numLigne==15) {
					
					numLigne = 0;
				}
				List<Integer> intList = stringToList(ligne) ;
				
				if(numLigne<5){ // elements de p
					
				}else if (numLigne<10){// elements de w
					
				}else if (numLigne<15){// elements de d
					
				}
				numLigne++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
