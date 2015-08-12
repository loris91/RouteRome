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
		System.out.println("Ora Inizio: " + this.inizio);
		System.out.println("Ora Fine: " + this.fine);
		List<Item> luoghiVisitabili = this.utente.getLuoghiVisitabili();
		System.out.println(luoghiVisitabili.size());
		LocalTime oraCorrente = this.inizio;
		int ora;
		while (oraCorrente.isBefore(fine)) {
			ora = oraCorrente.getHour();
			if (12 < ora && ora < 15) {
				// RISTORANTE
				System.out.println("Scelta ristorante");
			} else {
				Item nextStop = this.nextStop(luoghiVisitabili, oraCorrente);
				System.out.println(nextStop.getNome());
				this.itinerario.add(nextStop);
				int tempoDiVisita = nextStop.getDurata();
				System.out.println("Durata Visita: "
						+ tempoDiVisita);
				oraCorrente = oraCorrente
						.plusMinutes(tempoDiVisita);
				System.out.println("Ora aggiornata: " + oraCorrente);
				luoghiVisitabili.remove(nextStop);

			}
		}
		return this.itinerario;
	}

	public Item nextStop(List<Item> luoghiVisitabili, LocalTime oraCorrente) {
		System.out.println("\n***NEXT STOP***");
		CoordinateHelper helper = new CoordinateHelper();

		Item nextStop = null;
		float minDistance = 500000; // MAXDISTANCE
		float distance;
		for (Item item : luoghiVisitabili) {
			Coordinata coord = helper.getCoordinate(item.getVia());
			distance = this.coordinataCorrente.distFrom(coord);
			if (distance < minDistance) {
				minDistance = distance;
				this.coordinataCorrente = coord;
				nextStop = item;
				nextStop.setCoordinata(this.coordinataCorrente);
			}
		}
		if (nextStop != null) {
			this.coordinataCorrente = nextStop.getCoordinata();
			System.out.println("Coordinata Corrente : "
					+ coordinataCorrente.toString());
		}
		return nextStop;
	}

}
