package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Item;
import model.Luogo;
import model.Utente;
import model.facade.FacadeLuoghiVisitati;

public class AzioneConfermaItinerario extends Azione{

	@Override
	public String esegui(HttpServletRequest request, HttpServletResponse response) {
		FacadeLuoghiVisitati facadeLuoghiVisitati = new FacadeLuoghiVisitati();
		
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		List<Item> luoghiRaccomandati = (List<Item>) session.getAttribute("itinerario");
		
		facadeLuoghiVisitati.addList(utente.getUsername(), getListaIdLuoghiRaccomandati(luoghiRaccomandati));
		
		session.setAttribute("itinerario", luoghiRaccomandati);		
		
		//Converto la lista con Json 
		String json = new Gson().toJson(luoghiRaccomandati );
		request.setAttribute("mete", json);
		
		return "confermaPositiva";
	}

	private List<String> getListaIdLuoghiRaccomandati(List<Item> luoghiRaccomandati) {
		List<String> listaIdLuoghiRaccomandati = new ArrayList<String>();
		
		for (Item item : luoghiRaccomandati) {
			if (item instanceof Luogo) {
				listaIdLuoghiRaccomandati.add(((Luogo) item).getId());
			}
		}
		
		return listaIdLuoghiRaccomandati;
	}

}
