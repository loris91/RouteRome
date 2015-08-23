package test;

import java.util.Map;

import model.Coordinata;
import model.Luogo;
import model.facade.FacadeLuogo;

public class Main {

	public static void main(String[] args) {

		String id = "1";
		Map<String, Integer> tags = null;
		int durata = 0;
		String via = null;
		String nome = null;
		Coordinata coordinata = null;
		Luogo luogo = new Luogo(id, nome, via, durata, coordinata, tags);
		System.out.println(luogo.getId());

	}

}
