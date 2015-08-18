package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Itinerario {

	private Utente utente;
	private Coordinata coordinataCorrente;
	private List<Item> itinerario;
	private LocalTime inizio;
	private LocalTime fine;
	private LocalTime oraCorrente;
	private boolean pranzo;
	private boolean cena;

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
		this.oraCorrente=inizio;
		this.pranzo=false;
		this.cena=false;
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
		System.out.println("Coordinata di partenza: " + this.coordinataCorrente);
		List<Luogo> luoghiVisitabili = this.utente.getLuoghiVisitabili();
		System.out.println(luoghiVisitabili.size());
		int ora;
		while (this.oraCorrente.isBefore(this.fine)) {
			ora = oraCorrente.getHour();
			if (12 <= ora && ora < 15 && !pranzo) {
				// Non inserisco il ristorante nell'itinerario perchè non ha coordinate
//				Item ristorante = new Item("40", "Da Zia Pina", "Via vattela appesca", 60);
//				this.itinerario.add(ristorante);
				this.pranzo=true;
				this.oraCorrente.plusMinutes(80);				
				System.out.println("Ora di Pappa");
			} else {
				Luogo nextStop = this.nextStop(luoghiVisitabili);
				System.out.println(nextStop.getNome());
				this.itinerario.add(nextStop);
				
				luoghiVisitabili.remove(nextStop);

			}
		}
		return this.itinerario;
	}

	public Luogo nextStop(List<Luogo> luoghiVisitabili) {
		System.out.println("\n***NEXT STOP***");

		Luogo nextStop = null;
		float minDistance = 500000; // MAXDISTANCE
		float distance = 0;
		for (Luogo item : luoghiVisitabili) {
			distance = this.coordinataCorrente.distFrom(item.getCoordinata());
			if (distance < minDistance) {
				nextStop = item;
				minDistance = distance;
			}
		}
		if (nextStop != null) {
			this.coordinataCorrente = nextStop.getCoordinata();
			
			int tempoDiVisita = nextStop.getDurata();
			System.out.println("Durata Visita: " + tempoDiVisita);
			this.oraCorrente = oraCorrente.plusMinutes((int)distance/100);
			this. oraCorrente = oraCorrente.plusMinutes(tempoDiVisita);
			System.out.println("Ora aggiornata: " + oraCorrente);
			
			System.out.println("Coordinata Corrente : "
					+ coordinataCorrente.toString());
		}
		return nextStop;
	}

}
