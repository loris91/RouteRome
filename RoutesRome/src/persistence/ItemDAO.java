package persistence;

import java.util.List;

import model.Item;

public interface ItemDAO {
	
	boolean insert(Item item);
	
	List<Item> findByCategoria(String tag);	
	
	List<Item> findAll();

}
