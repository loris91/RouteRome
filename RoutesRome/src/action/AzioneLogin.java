package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;
import model.facade.FacadeQuestionario;
import model.facade.FacadeUtente;

public class AzioneLogin extends Azione {
	private FacadeUtente facadeUtente;
	private FacadeQuestionario facadeQuestionario;
	private Utente utente;

	@Override
	public String esegui(HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		this.facadeUtente = new FacadeUtente();
		this.facadeQuestionario = new FacadeQuestionario();

		if (username.isEmpty()) {
			request.setAttribute("erroreUsername", "Devi inserire un username.");
			return "accessoFallito";
		}

		if (password.isEmpty()) {
			request.setAttribute("errorePassword", "Inserisci la password.");
			return "accessoFallito";
		}
		
		this.utente = this.facadeUtente.findUtente(username);
		
		if(this.utente.getPassword()==null) {
			request.setAttribute("erroreAccesso", "Non è stato trovato alcun account 2MSoft con questo username.\n"+
								"Riprova reinserendo i dati richiesti.");
			return "accessoFallito";
		}

		if (!this.utente.getPassword().equals(password)) {
			request.setAttribute(
					"erroreAccesso",
					"L'username e/o la password inseriti non corrispondono ad alcun account 2MSoft.\n"
							+ "Se consideri i dati inseriti corretti, riprova dopo aver eliminato i cookies e i contenuti della cache del tuo browser.");
			return "accessoFallito";
		} else {
			HttpSession session = request.getSession();
			this.utente.setIncompilato(facadeQuestionario.existQuestionario(username));
			session.setAttribute("utente", this.utente);
			return "accessoEffettuato";
		}
	}

}
