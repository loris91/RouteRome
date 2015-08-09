package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AzioneRaccomandazione extends Azione{

	@Override
	public String esegui(HttpServletRequest request,
			HttpServletResponse response) {
		String inizio = request.getParameter("inizio");
		String fine = request.getParameter("fine");
		String indirizzo = request.getParameter("indirizzo");
		
		if (inizio.equals("null")){
			request.setAttribute("erroreInizio", "Devi selezionare un orario di inizio visita.");
			return "raccomandazioneFallita";
		}
		
		if (fine.equals("null")){
			request.setAttribute("erroreFine", "Devi selezionare un orario di fine visita.");
			return "raccomandazioneFallita";
		}
		
		if (indirizzo.isEmpty()){
			request.setAttribute("erroreIndirizzo", "Devi inserire un indirizzo.");
			return "raccomandazioneFallita";
		}
		
		//Creare una classe di supporto? che sulla base dell'orario e dell'indirizzo
		//restituisce dei punti di interesse che vengono visualizzati sulla mappa
		return "raccomandazionePositiva";
	}

}
