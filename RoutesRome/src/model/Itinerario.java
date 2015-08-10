package model;

import java.util.List;

public class Itinerario {

	private Utente utente;
	private Coordinata coordinata;
	private List<Item> itinerario;
	
	public Itinerario() {}
	
	public Itinerario(Utente utente, Coordinata coordinata) {
		super();
		this.utente = utente;
		this.coordinata = coordinata;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public Coordinata getCoordinata() {
		return this.coordinata;
	}
	
	public List<Item> getItinerario() {
		return this.itinerario;
	}
	
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public void setCoordinata(Coordinata coordinata) {
		this.coordinata = coordinata;
	}

	public void calcolaItinerario() {
		//TODO
	}
	
	public Item nextStop() {
		//TODO
		return null;
	}
}
