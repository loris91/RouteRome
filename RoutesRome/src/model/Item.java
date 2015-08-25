package model;

public abstract class Item {
	protected String id;
	protected String nome;
	protected String via;
	protected Coordinata coordinata;	

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

	@Override
	public String toString() {
		return "Item [id=" + id + ", nome=" + nome + ", via=" + via + ", coordinata=" + coordinata + "]";
	}

}
