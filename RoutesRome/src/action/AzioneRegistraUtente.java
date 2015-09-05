package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;
import model.facade.FacadeUtente;
import model.helper.UtenteHelper;

public class AzioneRegistraUtente extends Azione {
	private FacadeUtente facade;
	private UtenteHelper helper;
	private Utente utente;

	@Override
	public String esegui(HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession sessione = request.getSession();
			this.facade = new FacadeUtente();
			helper = new UtenteHelper(request);

			if (helper.verifica()) {
				this.utente = helper.makeUtente();
				if (!facade.addUtente(utente)) {
					request.setAttribute("erroreUsername", "Esiste gia' un utente con questo Username");
					return "registrazioneFallita";
				}
				sessione.setAttribute("utente", this.utente);
				return "registrazionePositiva";
			} else {
				return "registrazioneFallita";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore nella registrazione dell'utente");
			return "errore";
		}
	}

}
