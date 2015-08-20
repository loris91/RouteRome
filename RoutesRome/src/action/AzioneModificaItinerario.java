package action;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Coordinata;
import model.Item;
import model.Luogo;
import model.Itinerario;
import model.Utente;
import model.helper.LuoghiSgraditiHelper;

public class AzioneModificaItinerario extends Azione {

	@Override
	public String esegui(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		LocalTime inizio = (LocalTime) session.getAttribute("inizio");
		LocalTime fine = (LocalTime) session.getAttribute("fine");
		Coordinata posizione = (Coordinata) session.getAttribute("coordinata");
		List<Item> luoghiRaccomandati = (List<Item>) session.getAttribute("itinerario");
		List<Luogo> luoghiSgraditi = new ArrayList<Luogo>();
		LuoghiSgraditiHelper luoghiSgraditiHelper = new LuoghiSgraditiHelper();
		
		String[] sceltaLuoghi = request.getParameterValues("sceltaLuoghi");
		for (String string : sceltaLuoghi) {
			System.out.println(string);
			int idLuogo = Integer.parseInt(string);
			Luogo luogoSgradito = (Luogo) luoghiRaccomandati.get(idLuogo);
			luoghiSgraditi.add(luogoSgradito);
			luoghiSgraditiHelper.addTags(luogoSgradito,utente.getUsername());
			System.out.println(luoghiRaccomandati.get(idLuogo).getNome());
		}
		
		utente.removeItems(luoghiSgraditi);
		
		Itinerario itinerario = new Itinerario(utente, posizione, inizio, fine);
		List<Item> luoghiDaVisitare = itinerario.calcolaItinerario();
				
		
		session.setAttribute("utente", utente);
		session.setAttribute("inizio", inizio);
		session.setAttribute("fine", fine);
		session.setAttribute("coordinata", posizione);
		session.setAttribute("itinerario", luoghiDaVisitare);		
		
		//Converto la lista con Json 
		String json = new Gson().toJson(luoghiDaVisitare );
		request.setAttribute("mete", json);
		
		
		return "modificaPositiva";
	}	
}
