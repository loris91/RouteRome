package test;

import model.Luogo;
import model.facade.FacadeLuogo;

public class Main {

	public static void main(String[] args) {

		FacadeLuogo facade = new FacadeLuogo();
		
		Luogo luogo = null;
		int key = 0;
		String tag = null;
		Integer rate = null;
		facade.getLuoghi();

	}

}
