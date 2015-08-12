package model;

import java.util.List;

import model.facade.FacadeItem;

public class Utente {
	private String username;
	private String password;
	private String email;
	private String nome;
	private String cognome;
	private List<Item> luoghiVisitabili;

	public Utente() {
		super();
	}

	public Utente(String username, String password) {
		super();
		FacadeItem facadeItem = new FacadeItem();
		this.username = username;
		this.password = password;	
		this.luoghiVisitabili = facadeItem.getItems();	
	}
	
	public Utente(String username, String password, String email, String nome,
			String cognome) {
		super();
		FacadeItem facadeItem = new FacadeItem();
		this.username = username;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.luoghiVisitabili = facadeItem.getItems();		
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
	
	public List<Item> getLuoghiVisitabili() {
		return luoghiVisitabili;
	}

	public void setLuoghiVisitabili(List<Item> luoghiVisitabili) {
		this.luoghiVisitabili = luoghiVisitabili;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		return "Utente [username=" + username + ", password=" + password
				+ ", email=" + email + ", nome=" + nome + ", cognome="
				+ cognome + "]";
	}
}
