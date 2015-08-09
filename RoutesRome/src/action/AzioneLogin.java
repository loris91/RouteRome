package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;
import model.facade.FacadeUtente;

public class AzioneLogin extends Azione {
	private FacadeUtente facade;
	private Utente utente;

	@Override
	public String esegui(HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);

		if (username.isEmpty()) {
			request.setAttribute("erroreUsername", "Devi inserire un username.");
			return "accessoFallito";
		}

		if (password.isEmpty()) {
			request.setAttribute("errorePassword", "Inserisci la password.");
			return "accessoFallito";
		}
		

		this.facade = new FacadeUtente();
		this.utente = this.facade.findUtente(username);
		
		if(utente!=null)
			System.out.println(utente.toString());

		if (this.utente == null
				|| (!this.utente.getPassword().equals(password))) {
			request.setAttribute(
					"erroreAccesso",
					"L'username e/o la password inseriti non corrispondono ad alcun account 2MSoft.\n"
							+ "Se consideri i dati inseriti corretti, riprova dopo aver eliminato i cookies e i contenuti della cache del tuo browser.");
			return "accessoFallito";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("utente", this.utente);
			return "accessoEffettuato";
		}
	}

}
