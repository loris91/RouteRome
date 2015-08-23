package bho;

import java.util.ArrayList;
import java.util.List;

import model.facade.FacadeLuoghiVisitati;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		FacadeItem facade = new FacadeItem();
//		List<Item> items = facade.getItemByCategoria("monumento");
//		System.out.println(items.size());
//		
		
//		FacadeUtente facade = new FacadeUtente();
//		Utente u1 = new Utente("Alakay91", "asdfghjkl", "loris.marsico@gmail.com", "Loris", "Marsico");
//		System.out.println(facade.addUtente(u1));
		
		
//		Utile se si vuole far inserire l'orario all'utente
//		for (int i = 0; i < 60; i++) {
//			int v = (i/15)*15;
//			System.out.println("Partenza: " + i + "\t Modulo: "+v);
//			
//		}
		
		
		FacadeLuoghiVisitati facade = new FacadeLuoghiVisitati();
		List<String> valore = facade.getLuoghiVisitati("Alakay91");
		System.out.println(valore.isEmpty());
		
		List<String> lista = new ArrayList<String>();
		lista.add("13");
		lista.add("19");
		
		boolean valore2 = facade.addList("Alakay91", lista);
		System.out.println(valore2);
		
		List<String> valore3 = facade.getLuoghiVisitati("Alakay91");
		System.out.println(valore3.isEmpty());
		System.out.println();
		for (String string : valore3) {
			System.out.println(string);
		}		

	}

}
