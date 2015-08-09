package persistence;

import model.Utente;

public interface UtenteDAO {

	boolean insert(Utente utente);

	boolean delete(Utente utente);

	boolean deleteAll();

	Utente findByUsername(String username);

	public boolean findByEmail(String email);

}
