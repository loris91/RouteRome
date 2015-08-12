package model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import bho.CoordinateHelper;

public class Itinerario {

	private Utente utente;
	private Coordinata coordinataCorrente;
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
		this.itinerario = new ArrayList<Item>();
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

	public List<Item> calcolaItinerario() {
		List<Item> luoghiVisitabili = this.utente.getLuoghiVisitabili();
		System.out.println(luoghiVisitabili.size());
		LocalTime oraCorrente = this.inizio;
		int ora = oraCorrente.getHour();
		
		if(12<ora && ora<15) {
			//RISTORANTE
			System.out.println("Scelta ristorante");
		}
		else {
			Item nextStop = this.nextStop(luoghiVisitabili,oraCorrente);
			this.itinerario.add(nextStop);
		}
		
		return this.itinerario;
	}
	
	public Item nextStop(List<Item> luoghiVisitabili, LocalTime oraCorrente) {
		CoordinateHelper helper = new CoordinateHelper();
		
		Item nextStop = null;
		float minDistance = 500000; //MAXDISTANCE
		float distance;
		for (Item item : luoghiVisitabili) {
			Coordinata coord = helper.getCoordinate(item.getVia());
			distance = this.coordinataCorrente.distFrom(coord);
			if(distance < minDistance) {
				nextStop = item;
				minDistance = distance;
			}
		}
		if (nextStop != null) {
			this.coordinataCorrente = nextStop.getCoordinata();
			LocalTime tempoDiVisita = LocalTime.of(0, 0);
			tempoDiVisita.plusMinutes(nextStop.getDurata());
			oraCorrente = oraCorrente.plusMinutes(tempoDiVisita.getMinute());
		}
		return nextStop;
	}
	
}
