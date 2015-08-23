package test;

import java.util.List;

import model.Luogo;
import model.facade.FacadeLuogo;
import model.facade.FacadeTagRimossi;

public class Main {

	public static void main(String[] args) {

		FacadeLuogo facade = new FacadeLuogo();
		
		List<Luogo> variabile = facade.getLuogoByCategoria("Musei", 1);
		
		
		System.out.println(variabile.size());
		
		for (Luogo luogo : variabile) {
			System.out.println(luogo.getNome());
		}

	}

}
