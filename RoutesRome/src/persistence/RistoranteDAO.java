package persistence;

import java.util.List;

import model.Ristorante;

public interface RistoranteDAO {
	
	boolean insert(Ristorante ristorante);
		
	List<Ristorante> findAll();

}
