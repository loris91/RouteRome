package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.facade.FacadeRistorante;

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

	public Itinerario(Utente utente, Coordinata coordinataCorrente, LocalTime inizio, LocalTime fine) {
		super();
		this.utente = utente;
		this.coordinataCorrente = coordinataCorrente;
		this.inizio = inizio;
		this.fine = fine;
		this.itinerario = new ArrayList<Item>();
		this.oraCorrente = inizio;
		this.pranzo = false;
		this.cena = false;
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

		List<Luogo> luoghiVisitabili = new ArrayList<Luogo>(this.utente.getLuoghiVisitabili());
		int ora;
		while (this.oraCorrente.isBefore(this.fine)) {
			ora = oraCorrente.getHour();
			if (12 <= ora && ora < 15 && !pranzo) {
				Ristorante nextStop = this.trovaRistorante();
				this.itinerario.add(nextStop);
				this.pranzo = true;
				this.oraCorrente.plusMinutes(80);
			} else {
				if (19 <= ora && ora < 21 && !cena) {
					Ristorante nextStop = this.trovaRistorante();
					this.itinerario.add(nextStop);
					this.cena = true;
					this.oraCorrente.plusMinutes(80);
				} else {
					Luogo nextStop = this.nextStop(luoghiVisitabili);
					this.itinerario.add(nextStop);
					luoghiVisitabili.remove(nextStop);

				}
			}
		}
		return this.itinerario;
	}

	private Ristorante trovaRistorante() {
		Ristorante nextStop = null;
		float minDistance = 500000; // MAXDISTANCE
		float distance = 0;
		FacadeRistorante facade = new FacadeRistorante();
		List<Ristorante> ristoranti = facade.getRistoranti();
		for (Ristorante ristorante : ristoranti) {
			distance = this.coordinataCorrente.distFrom(ristorante.getCoordinata());
			if (distance < minDistance) {
				nextStop = ristorante;
				minDistance = distance;
			}
		}
		if (nextStop != null) {
			this.coordinataCorrente = nextStop.getCoordinata();

			int tempoDiVisita = 60;
			this.oraCorrente = oraCorrente.plusMinutes((int) distance / 100);
			this.oraCorrente = oraCorrente.plusMinutes(tempoDiVisita);
		}
		return nextStop;

	}

	public Luogo nextStop(List<Luogo> luoghiVisitabili) {
		Luogo nextStop = null;
		float minDistance = 500000; // MAXDISTANCE
		float distance = 0;
		for (Luogo luogo : luoghiVisitabili) {
			distance = this.coordinataCorrente.distFrom(luogo.getCoordinata());
			if (distance < minDistance) {
				nextStop = luogo;
				minDistance = distance;
			}
		}
		if (nextStop != null) {
			this.coordinataCorrente = nextStop.getCoordinata();

			int tempoDiVisita = nextStop.getDurata();
			this.oraCorrente = oraCorrente.plusMinutes((int) distance / 100);
			this.oraCorrente = oraCorrente.plusMinutes(tempoDiVisita);
		}
		return nextStop;
	}

}
