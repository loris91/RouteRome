package model;

import com.sun.javafx.collections.MappingChange.Map;

//Ora come ora neanche serve visto che interagirà solo con il DB TagRImossi. Se in futuro vogliamo renderli persistenti allora DAJE!

public class Questionario {

	private Utente utente;
	private Map<String, Integer> preferenze;
	
	public Questionario(Utente utente, Map<String, Integer> preferenze) {
		super();
		this.utente = utente;
		this.preferenze = preferenze;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public Map<String, Integer> getPreferenze() {
		return preferenze;
	}
	
	public void setPreferenze(Map<String, Integer> preferenze) {
		this.preferenze = preferenze;
	}
	
	
}
