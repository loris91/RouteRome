package test;

import java.util.Map;

import model.Coordinata;
import model.Luogo;

public class Main {

	public static void main(String[] args) {

		String id = "1";
		Map<String, Integer> tags = null;
		int durata = 55;
		String via = null;
		String nome = "prova";
		Coordinata coordinata = null;
		Luogo luogo = new Luogo(id, nome, via, coordinata, durata, tags);
		System.out.println(luogo.getId());
		System.out.println(luogo.getNome());
		System.out.println(luogo.getDurata());

	}

}
