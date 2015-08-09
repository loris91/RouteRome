package persistence;

import model.Utente;

public interface UtenteDAO {

	boolean insert(Utente Utente);

	boolean delete(Utente Utente);

	boolean deleteAll();

	Utente findByUsername(String username);

	public boolean findByEmail(String email);

}
