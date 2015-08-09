package model;

public class Item {
	private String id;
	private String nome;
	private String via;
	private String categoria;
	private int durata;
	private Coordinata coordinata;
	
	public Item(String id, String nome, String via, int durata) {
		super();
		this.id = id;
		this.nome = nome;
		this.via = via;
		this.durata = durata;
	}
	
	public Item(String id, String nome, String via, String categoria, int durata,
			Coordinata coordinata) {
		super();
		this.id = id;
		this.nome = nome;
		this.via = via;
		this.categoria = categoria;
		this.durata = durata;
		this.coordinata = coordinata;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}

	public String getVia() {
		return this.via;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public int getDurata() {
		return this.durata;
	}

	public Coordinata getCoordinata() {
		return this.coordinata;
	}
}
