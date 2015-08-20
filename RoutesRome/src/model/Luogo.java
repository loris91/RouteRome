package model;

import java.util.HashMap;
import java.util.Map;

public class Luogo extends Item {
	private String id;
	private String nome;
	private String via;
	private int durata;
	private Coordinata coordinata;
	private String immagine;
	private Map<String, Integer> tags;
	
	
	public Luogo(String id, String nome, String via, int durata, Coordinata coordinata,
			Map<String, Integer> tags) {
		super();
		this.id = id;
		this.nome = nome;
		this.via = via;
		this.durata = durata;
		this.coordinata = coordinata;
		this.immagine = "images\\luoghi\\" + nome + ".jpg";
		this.tags = tags;
	}
	
	public Luogo(String id, String nome, String via, int durata, Coordinata coordinata) {
		super();
		this.id = id;
		this.nome = nome;
		this.via = via;
		this.durata = durata;
		this.coordinata = coordinata;
		this.immagine = "images\\luoghi\\" + nome + ".jpg";
		this.tags = new HashMap<String, Integer>();
	}

	public Luogo(String id, String nome, String via, int durata) {
		super();
		this.id = id;
		this.nome = nome;
		this.via = via;
		this.durata = durata;
		this.immagine = "images\\luoghi\\" + nome + ".jpg";
		this.tags = new HashMap<String, Integer>();
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getVia() {
		return via;
	}

	public int getDurata() {
		return durata;
	}

	public Coordinata getCoordinata() {
		return coordinata;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public void setCoordinata(Coordinata coordinata) {
		this.coordinata = coordinata;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public Map<String, Integer> getTags() {
		return tags;
	}

	public void setTags(Map<String, Integer> tags) {
		this.tags = tags;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Luogo other = (Luogo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
