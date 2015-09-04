package model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.facade.FacadeLuoghiVisitati;
import model.facade.FacadeLuogo;
import model.facade.FacadeQuestionario;
import model.facade.FacadeTagRimossi;

public class Utente {
	private String username;
	private String password;
	private String email;
	private String nome;
	private String cognome;
	private List<Luogo> luoghiVisitabili;
	private boolean incompilato;

	public Utente() {
		super();
	}

	public Utente(String username, String password) {
		super();
		FacadeLuogo facadeLuogo = new FacadeLuogo();
		this.username = username;
		this.password = password;
		this.luoghiVisitabili = filtraLuoghi(facadeLuogo.getLuoghi());
		this.incompilato = true;
	}

	public Utente(String username, String password, String email, String nome, String cognome) {
		super();
		FacadeLuogo facadeLuogo = new FacadeLuogo();
		this.username = username;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.luoghiVisitabili = filtraLuoghi(facadeLuogo.getLuoghi());
		this.incompilato = true;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Luogo> getLuoghiVisitabili() {
		return luoghiVisitabili;
	}

	public boolean getIncompilato() {
		return this.incompilato;
	}

	public void setLuoghiVisitabili(List<Luogo> luoghiVisitabili) {
		this.luoghiVisitabili = luoghiVisitabili;
	}

	public void setIncompilato(boolean incompilato) {
		this.incompilato = incompilato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + ", password=" + password + ", email=" + email + ", nome=" + nome
				+ ", cognome=" + cognome + "]";
	}

	public void removeItems(List<Luogo> lista) {
		List<Luogo> list = this.luoghiVisitabili;
		list.removeAll(lista);
		this.setLuoghiVisitabili(list);
	}

	// Filtraggio della lista secondo i gusti
	private List<Luogo> filtraLuoghi(List<Luogo> luoghi) {

		luoghi = this.rimuoviVisitati(luoghi);
		luoghi = this.rimuoviSgraditi(luoghi);
		luoghi = this.rimuoviQuestionario(luoghi);
		return luoghi;
	}

	private List<Luogo> rimuoviVisitati(List<Luogo> luoghiVisitabili) {
		FacadeLuoghiVisitati facadeLuoghiVisitati = new FacadeLuoghiVisitati();
		FacadeLuogo facadeLuogo = new FacadeLuogo();

		for (String string : facadeLuoghiVisitati.getLuoghiVisitati(this.username)) {
			Luogo luogo = facadeLuogo.getLuogoById(string);
			luoghiVisitabili.remove(luogo);
		}
		return luoghiVisitabili;
	}

	private List<Luogo> rimuoviSgraditi(List<Luogo> luoghiVisitabili) {
		FacadeTagRimossi facadeTagRimossi = new FacadeTagRimossi();
		FacadeLuogo facadeLuogo = new FacadeLuogo();

		Map<String, Integer> tags = facadeTagRimossi.getTagRimossi(this.username);
		Set<String> keys = tags.keySet();

		for (String key : keys) {
			for (int i = tags.get(key); i > 0; i--) {
				List<Luogo> luoghi = facadeLuogo.getLuogoByCategoria(key, i);

				if (luoghi != null)
					luoghiVisitabili.removeAll(luoghi);
			}
		}

		return luoghiVisitabili;
	}

	private List<Luogo> rimuoviQuestionario(List<Luogo> luoghiVisitabili) {
		FacadeQuestionario facadeQuestionario = new FacadeQuestionario();
		FacadeLuogo facadeLuogo = new FacadeLuogo();

		Map<String, Integer> preferenze = facadeQuestionario.getQuestionarioByID(this.username).getPreferenze();
		if (preferenze != null) {
			Set<String> keys = preferenze.keySet();

			for (String key : keys) {
				for (int i = preferenze.get(key); i > 0; i--) {
					List<Luogo> luoghi = facadeLuogo.getLuogoByCategoria(key, i);

					if (luoghi != null)
						luoghiVisitabili.removeAll(luoghi);
				}
			}
		}

		return luoghiVisitabili;
	}
}