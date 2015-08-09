package persistence;

import java.util.List;

import model.Item;

public interface ItemDAO {
	
	boolean insert(int key,Item item);
	
	List<Item> findByCategoria(String tag);	
	
	List<Item> findAll();

}
