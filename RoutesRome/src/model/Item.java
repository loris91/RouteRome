package model;

public class Item {
	private String nome;
	private String via;
	private String categoria;
	private int durata;
	private Coordinata coordinata;
	
	public Item(String nome, String via, String categoria, int durata,
			Coordinata coordinata) {
		super();
		this.nome = nome;
		this.via = via;
		this.categoria = categoria;
		this.durata = durata;
		this.coordinata = coordinata;
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
}
