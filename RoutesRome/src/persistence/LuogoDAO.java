package persistence;

import java.util.List;

import model.Luogo;

public interface LuogoDAO {
	
	boolean insert(Luogo luogo);
	
	List<Luogo> findByCategoria(String tag);	
	
	List<Luogo> findAll();

}
