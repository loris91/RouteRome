package action;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Itinerario;
import model.Utente;

public class AzioneRaccomandazione extends Azione{

	@Override
	public String esegui(HttpServletRequest request,
			HttpServletResponse response) {
		String inizio = request.getParameter("inizio");
		String fine = request.getParameter("fine");
		String posizione = request.getParameter("posizione");
		HttpSession sessione = request.getSession();
		Utente utente = (Utente) sessione.getAttribute("utente");
		
		System.out.println(inizio);
		System.out.println(fine);
		System.out.println(posizione);
		
		if (inizio.equals("null")){
			request.setAttribute("erroreInizio", "Devi selezionare un orario di inizio visita.");
			return "raccomandazioneFallita";
		}
		
		if (fine.equals("null")){
			request.setAttribute("erroreFine", "Devi selezionare un orario di fine visita.");
			return "raccomandazioneFallita";
		}
		
		if (posizione.isEmpty()){
			request.setAttribute("erroreIndirizzo", "Devi inserire una posizione.");
			return "raccomandazioneFallita";
		}
		
		
		
		
		
		//Creare una classe di supporto? che sulla base dell'orario e dell'indirizzo
		//restituisce dei punti di interesse che vengono visualizzati sulla mappa
		//Itinerario itinerario = new Itinerario(utente, posizione, inizio, fine);
		
		return "raccomandazionePositiva";
	}

}
