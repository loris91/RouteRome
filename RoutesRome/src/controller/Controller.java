package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Azione;

/**
 * Servlet implementazione class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> comando2azione;
	private Map<String, String> esito2pagina;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		String command = this.leggiComando(request.getServletPath());
		String action = this.comando2azione.get(command);
		

		if (action == null) {
			nextPage = "/error.jsp";
		} else {
			Azione azione = null;
			try {
				azione = (Azione) Class.forName(action).newInstance();
				String esitoAzione = azione.esegui(request, response);
				nextPage = this.esito2pagina.get(esitoAzione);
			} catch (InstantiationException e) {
				nextPage = "/error.jsp";
			} catch (IllegalAccessException e) {
				nextPage = "/error.jsp";
			} catch (ClassNotFoundException e) {
				nextPage = "/error.jsp";
			}
		}

		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

	/**
	 * Dato un path ritorna il rispettivo comando
	 * 
	 * @param servletPath
	 * @return string
	 */
	private String leggiComando(String servletPath) {
		StringBuffer str = new StringBuffer(servletPath);
		return str.substring(1, str.lastIndexOf(".do"));
	}

	/**
	 * Implementazione caricamento delle mappe comando2azione e esito2pagina
	 */
	public void init() {
		/*
		 * Mappa comando2azione, ricevuto un comando da una jsp ( *.do ) ritorna
		 * la rispettiva azione da eseguire (action.Azione* )
		 */
		this.comando2azione = new HashMap<String, String>();
		this.comando2azione.put("login", "action.AzioneLogin");
		this.comando2azione.put("registra", "action.AzioneRegistraUtente");
		this.comando2azione.put("raccomanda", "action.AzioneRaccomandazione");
		this.comando2azione.put("modifica", "action.AzioneModificaItinerario");
		this.comando2azione.put("conferma", "action.AzioneConfermaItinerario");
		this.comando2azione.put("questionario", "action.AzioneConfermaQuestionario");

		/*
		 * Mappa esito2pagina, ricevuto l'esito di un'azione ritorna rispettiva
		 * pagina da visualizzare
		 */
		this.esito2pagina = new HashMap<String, String>();
		this.esito2pagina.put("accessoFallito", "/index.jsp");
		this.esito2pagina.put("accessoEffettuato", "/selezione.jsp");
		this.esito2pagina.put("registrazioneFallita", "/registrazione.jsp");
		this.esito2pagina.put("registrazionePositiva", "/selezione.jsp");
		this.esito2pagina.put("raccomandazioneFallita", "/selezione.jsp");
		this.esito2pagina.put("raccomandazionePositiva", "/mappa.jsp");
		this.esito2pagina.put("modificaFallita", "/error.jsp");
		this.esito2pagina.put("modificaPositiva", "/mappa.jsp");
		this.esito2pagina.put("confermaFallita", "/error.jsp");
		this.esito2pagina.put("confermaPositiva", "/itinerario.jsp");
		this.esito2pagina.put("compilazionePositiva", "/selezione.jsp");
		this.esito2pagina.put("compilazioneNegativa", "/error.jsp");		
		this.esito2pagina.put("errore", "/error.jsp");
	}
}
