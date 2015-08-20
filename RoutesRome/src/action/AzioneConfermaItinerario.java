package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Item;
import model.Utente;

public class AzioneConfermaItinerario extends Azione{

	@Override
	public String esegui(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		List<Item> luoghiRaccomandati = (List<Item>) session.getAttribute("itinerario");
		
		session.setAttribute("itinerario", luoghiRaccomandati);		
		
		//Converto la lista con Json 
		String json = new Gson().toJson(luoghiRaccomandati );
		request.setAttribute("mete", json);
		
		return "confermaPositiva";
	}

}
