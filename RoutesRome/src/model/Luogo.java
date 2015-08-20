package model;

import java.util.HashMap;
import java.util.Map;

public class Luogo extends Item{
	private String id;
	private String nome;
	private String via;
	private String categoria;
	private int durata;
	private Coordinata coordinata;
	private String immagine;
	private Map<String, Integer> tags;
	
	public Luogo(String id, String nome, String via, int durata) {
		super();
		this.id = id;
		this.nome = nome;
		this.via = via;
		this.durata = durata;
		this.immagine = "images\\luoghi\\"+nome+".jpg";
		this.tags = new HashMap<String, Integer>();
	}
	
	public Luogo(String id, String nome, String via, String categoria, int durata,
			Coordinata coordinata) {
		super();
		this.id = id;
		this.nome = nome;
		this.via = via;
		this.categoria = categoria;
		this.durata = durata;
		this.coordinata = coordinata;
		this.immagine = "images\\luoghi\\"+nome+".jpg";
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

	public String getCategoria() {
		return categoria;
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

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

}
