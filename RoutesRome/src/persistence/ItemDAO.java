package persistence;

import java.util.List;

import model.Luogo;

public interface ItemDAO {
	
	boolean insert(Luogo item);
	
	List<Luogo> findByCategoria(String tag);	
	
	List<Luogo> findAll();

}
