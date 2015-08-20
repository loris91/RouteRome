package bho;

import model.Utente;
import model.facade.FacadeUtente;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		FacadeItem facade = new FacadeItem();
//		List<Item> items = facade.getItemByCategoria("monumento");
//		System.out.println(items.size());
//		
		
		FacadeUtente facade = new FacadeUtente();
		Utente u1 = new Utente("Alakay91", "asdfghjkl", "loris.marsico@gmail.com", "Loris", "Marsico");
		System.out.println(facade.addUtente(u1));
		
		
//		Utile se si vuole far inserire l'orario all'utente
//		for (int i = 0; i < 60; i++) {
//			int v = (i/15)*15;
//			System.out.println("Partenza: " + i + "\t Modulo: "+v);
//			
//		}
		
		

	}

}
