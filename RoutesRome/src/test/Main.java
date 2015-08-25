package test;

import java.util.List;
import model.Luogo;
import model.facade.FacadeLuogo;

public class Main {

	public static void main(String[] args) {

		FacadeLuogo facade = new FacadeLuogo();

		String tag = "Musei";
		int rate = 2;
		List<Luogo> luoghi = facade.getLuogoByCategoria(tag, rate);
		
		for (Luogo luogo : luoghi) {
			System.out.println(luogo.toString());
		}
	}

}
