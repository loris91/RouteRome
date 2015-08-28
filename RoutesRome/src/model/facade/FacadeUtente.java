package model.facade;

import model.Utente;
import persistence.clusterPoint.UtenteDAOClusterPoint;

public class FacadeUtente {
	private UtenteDAOClusterPoint dao;
	
	public FacadeUtente() {
		super();
		this.dao = new UtenteDAOClusterPoint();
	}

	public boolean addUtente(Utente utente) {
		return this.dao.insert(utente);
	}

	public boolean deleteUtente(Utente utente){
		return this.dao.delete(utente);
	}
	
	public boolean deleteAll() {
		return this.dao.deleteAll();
	}
	
	public Utente findUtente(String username){
		return this.dao.findByUsername(username);
	}
	
	public boolean hasEmail(String email){
		return this.dao.findByEmail(email);
	}

}
