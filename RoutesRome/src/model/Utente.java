package model;

import java.util.ArrayList;
import java.util.List;

public class Utente {
	protected String username;
	private String password;
	private String email;
	private String nome;
	private String cognome;
	private boolean incompilato;
	protected List<Luogo> luoghiVisitabili;

	public Utente() {
		super();
		this.luoghiVisitabili = new ArrayList<Luogo>();
	}

	public Utente(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.incompilato = true;
		this.luoghiVisitabili = new ArrayList<Luogo>();
	}

	public Utente(String username, String password, String email, String nome, String cognome) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.incompilato = true;
		this.luoghiVisitabili = new ArrayList<Luogo>();
	}	

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public boolean isIncompilato() {
		return incompilato;
	}

	public List<Luogo> getLuoghiVisitabili() {
		return luoghiVisitabili;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setIncompilato(boolean incompilato) {
		this.incompilato = incompilato;
	}

	public void setLuoghiVisitabili(List<Luogo> luoghiVisitabili) {
		this.luoghiVisitabili = luoghiVisitabili;
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

}