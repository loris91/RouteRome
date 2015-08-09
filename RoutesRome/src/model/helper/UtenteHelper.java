package model.helper;

import javax.servlet.http.HttpServletRequest;

import model.Utente;

public class UtenteHelper {
	private String username;
	private String password;
	private String passwordConferma;
	private String email;
	private String emailConferma;
	private String nome;
	private String cognome;
	private HttpServletRequest richiesta;

	public UtenteHelper(HttpServletRequest request) {
		this.username = request.getParameter("username");
		this.password = request.getParameter("password");
		this.passwordConferma = request.getParameter("passwordConferma");
		this.email = request.getParameter("email");
		this.emailConferma = request.getParameter("emailConferma");
		this.nome = request.getParameter("nome");
		this.cognome = request.getParameter("cognome");
		this.richiesta = request;
	}

	public boolean verifica() {
		boolean ver = true;
		if (username.isEmpty()) {
			richiesta.setAttribute("erroreUsername",
					"Devi inserire un username.");
			ver = false;
		}
		if (password.isEmpty() || passwordConferma.isEmpty()) {
			richiesta.setAttribute("errorePassword", "Inserisci la password.");
			ver = false;
		}
		if (password.length()<8){
			richiesta.setAttribute("errorePassword", "Password troppo corta, almeno 8 caratteri!");
			
		}
		if (email.isEmpty() || emailConferma.isEmpty()) {
			richiesta.setAttribute("erroreEmail", "Indirizzo e-mail mancante.");
			ver = false;
		}
		if (nome.isEmpty()) {
			richiesta.setAttribute("erroreNome", "Devi inserire il tuo nome.");
			ver = false;
		}
		if (cognome.isEmpty()) {
			richiesta.setAttribute("erroreCognome",
					"Devi inserire il tuo cognome.");
			ver = false;
		}
		
		if (!password.equals(passwordConferma)) {
			richiesta
					.setAttribute("errorePasswordDiverse",
							"Verifica che le password indicate siano uguali e riprova.");
			ver = false;
		}
		if (!email.equals(emailConferma)) {
			richiesta.setAttribute("erroreEmailDiverse",
					"Verifica che le e-mail indicate siano uguali e riprova.");
			ver = false;
		}

		return ver;

	}

	public Utente makeUtente() {
		String username = this.username;
		String password = this.password;
		String email = this.email;
		String nome = this.nome;
		String cognome = this.cognome;

		Utente utente = new Utente(username, password, email, nome, cognome);
		return utente;
	}

}
