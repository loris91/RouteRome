package test;

import java.util.List;

import model.Item;
import model.facade.FacadeItem;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		FacadeItem facade = new FacadeItem();
		
		List<Item> lista = facade.getItems();
		
		System.out.println(lista.size());
		
		for (Item item : lista) {
			System.out.println(item.getNome());
			System.out.println(item.getCoordinata());
			System.out.println(item.getCoordinata().getLatitudine());
			System.out.println(item.getCoordinata().getLongitudine());
		}
		
	}

}
