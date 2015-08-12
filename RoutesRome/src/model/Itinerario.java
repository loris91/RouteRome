package model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Itinerario {

	private Utente utente;
	private Coordinata coordinataCorrente;
	private LocalTime oraCorrente;
	private List<Item> itinerario;
	private LocalTime inizio;
	private LocalTime fine;
	
	public Itinerario() {
		super();
	}

	public Itinerario(Utente utente, Coordinata coordinataCorrente,
			LocalTime inizio, LocalTime fine) {
		super();
		this.utente = utente;
		this.coordinataCorrente = coordinataCorrente;
		this.inizio = inizio;
		this.fine = fine;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public Coordinata getCoordinata() {
		return this.coordinataCorrente;
	}
	
	public List<Item> getItinerario() {
		return this.itinerario;
	}
	
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public void setCoordinata(Coordinata coordinata) {
		this.coordinataCorrente = coordinata;
	}

	public void calcolaItinerario() {
		List<Item> luoghiVisitabili = this.utente.getLuoghiVisitabili();
		int ora = this.oraCorrente.getHour();
		if(12<ora || ora<15) {
			//RISTORANTE
		}
		else {
			Item nextStop = this.nextStop(luoghiVisitabili);
			this.itinerario.add(nextStop);
		}
	}
	
	public Item nextStop(List<Item> luoghiVisitabili) {
		Item nextStop = null;
		float minDistance = 500000; //MAXDISTANCE
		float distance;
		for (Item item : luoghiVisitabili) {
			distance = this.coordinataCorrente.distFrom(item.getCoordinata());
			if(distance < minDistance) {
				nextStop = item;
				minDistance = distance;
			}
		}
		if (nextStop != null) {
			this.coordinataCorrente = nextStop.getCoordinata();
			LocalTime tempoDiVisita = LocalTime.of(0, 0);
			tempoDiVisita.plusMinutes(nextStop.getDurata());
			this.oraCorrente = this.oraCorrente.plusMinutes(tempoDiVisita.getMinute());
		}
		return nextStop;
	}
}
