package model;

public abstract class Item {
	private String id;
	private String nome;
	private String via;
	private Coordinata coordinata;	

	public Item(String id, String nome, String via, Coordinata coordinata) {
		this.id = id;
		this.nome = nome;
		this.via = via;
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

	public Coordinata getCoordinata() {
		return this.coordinata;
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

	public void setCoordinata(Coordinata coordinata) {
		this.coordinata = coordinata;
	}

}
