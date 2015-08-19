package action;

import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bho.CoordinateHelper;
import model.Coordinata;
import model.Item;
import model.Itinerario;
import model.Utente;

public class AzioneRaccomandazione extends Azione {

	@Override
	public String esegui(HttpServletRequest request, HttpServletResponse response) {
		String inizioInput = request.getParameter("inizio");
		String fineInput = request.getParameter("fine");
		String posizioneInput = request.getParameter("posizione");
		HttpSession sessione = request.getSession();
		Utente utente = (Utente) sessione.getAttribute("utente");
		
		System.out.println(utente.getUsername());
		System.out.println(utente.getPassword());

		System.out.println(inizioInput);
		System.out.println(fineInput);
		System.out.println(posizioneInput);

		if (inizioInput.equals("null")) {
			request.setAttribute("erroreInizio", "Devi selezionare un orario di inizio visita.");
			return "raccomandazioneFallita";
		}

		if (fineInput.equals("null")) {
			request.setAttribute("erroreFine", "Devi selezionare un orario di fine visita.");
			return "raccomandazioneFallita";
		}

		if (posizioneInput.isEmpty()) {
			request.setAttribute("erroreIndirizzo", "Devi inserire una posizione.");
			return "raccomandazioneFallita";
		}

		
		// Conversione delle variabili da stringhe
		LocalTime inizio = LocalTime.parse(inizioInput);
		LocalTime fine = LocalTime.parse(fineInput);
		Coordinata posizione = converti(posizioneInput);
		
		
		Itinerario itinerario = new Itinerario(utente, posizione, inizio, fine);
		List<Item> luoghiDaVisitare = itinerario.calcolaItinerario();
		
//		System.out.println(luoghiDaVisitare.size());
//		for (Item item : luoghiDaVisitare) {
//			System.out.println(item.getNome());
//		}		
		
		HttpSession session = request.getSession();
		session.setAttribute("utente", utente);
		session.setAttribute("inizio", inizio);
		session.setAttribute("fine", fine);
		session.setAttribute("coordinata", posizione);
		session.setAttribute("itinerario", luoghiDaVisitare);		
		
		//Converto la lista con Json 
		String json = new Gson().toJson(luoghiDaVisitare );
		request.setAttribute("mete", json);
		

		return "raccomandazionePositiva";
	}

	private Coordinata converti(String posizioneInput) {
		Coordinata coordinata = null;

		if (posizioneInput.charAt(0) == '1' || posizioneInput.charAt(0) == '2' || posizioneInput.charAt(0) == '3'
				|| posizioneInput.charAt(0) == '4' || posizioneInput.charAt(0) == '5' || posizioneInput.charAt(0) == '6'
				|| posizioneInput.charAt(0) == '7' || posizioneInput.charAt(0) == '8'
				|| posizioneInput.charAt(0) == '9') {
			double d1 = Double.parseDouble(posizioneInput.split(",", 2)[0]);
			double d2 = Double.parseDouble(posizioneInput.split(",", 2)[1]);
			coordinata = new Coordinata(d1, d2);			
		} else {
			CoordinateHelper helper = new CoordinateHelper();
			coordinata = helper.getCoordinate(posizioneInput);
		}
		
		return coordinata;
	}

}
