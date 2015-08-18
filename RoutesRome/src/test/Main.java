package test;

import java.util.List;

import model.Luogo;
import model.facade.FacadeLuogo;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		FacadeLuogo facade = new FacadeLuogo();
		
		List<Luogo> lista = facade.getItems();
		
		System.out.println(lista.size());
		
		for (Luogo item : lista) {
			System.out.println(item.getNome());
			System.out.println(item.getCoordinata());
			System.out.println(item.getCoordinata().getLatitudine());
			System.out.println(item.getCoordinata().getLongitudine());
		}
	}
		

}
