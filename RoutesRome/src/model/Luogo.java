package model;

import java.util.Map;
import java.util.Set;

public class Luogo extends Item {
	private String immagine;
	protected int durata;
	private Map<String, Integer> tags;

	public Luogo(String id, String nome, String via, Coordinata coordinata, int durata, Map<String, Integer> tags) {
		super(id, nome, via, coordinata);
		this.durata = durata;
		this.immagine = "images\\luoghi\\" + nome + ".jpg";
		this.tags = tags;
	}

	public int getDurata() {
		return this.durata;
	}

	public String getImmagine() {
		return this.immagine;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public Map<String, Integer> getTags() {
		return this.tags;
	}

	public void setTags(Map<String, Integer> tags) {
		this.tags = tags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : id.hashCode());
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

	public boolean hasTag(String tag, Integer rate) {
		boolean esito = false;
		Set<String> keys = this.tags.keySet();
		for (String key : keys) {
			if(key.equals(tag) && this.tags.get(key).equals(rate)) {
				esito = true;
			}
		}
		return esito;
	}

}
