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
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getVia() {
		return via;
	}

	public Coordinata getCoordinata() {
		return coordinata;
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
