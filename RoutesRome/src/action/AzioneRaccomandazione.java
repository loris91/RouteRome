package action;

import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Coordinata;
import model.Item;
import model.Itinerario;
import model.Utente;
import model.helper.CoordinateHelper;

public class AzioneRaccomandazione extends Azione {

	@Override
	public String esegui(HttpServletRequest request, HttpServletResponse response) {
		try {
			String inizioInput = request.getParameter("inizio");
			String fineInput = request.getParameter("fine");
			String posizioneInput = request.getParameter("posizione");
			HttpSession sessione = request.getSession();
			Utente utente = (Utente) sessione.getAttribute("utente");

			System.out.println("Orario di inizio itinerario: "+ inizioInput);
			System.out.println("Orario di fine itinerario: "+ fineInput);
			System.out.println("Posizione iniziale: "+ posizioneInput);

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
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore nella raccomandazione");
			return "errore";
		}
	}

	private Coordinata converti(String posizioneInput) {
		Coordinata coordinata = null;

		if (posizioneInput.substring(0,1).matches("[0-9]")) {
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
