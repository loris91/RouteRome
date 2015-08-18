package model;

public class Ristorante extends Item{
	private String id;
	private String nome;
	private String via;
	private Coordinata coordinata;
	private String immagine;
	
	public Ristorante(String id, String nome, String via) {
		super();
		this.id = id;
		this.nome = nome;
		this.via = via;
		this.immagine = "images\\ristoranti\\"+nome+".jpg";
	}
	
	public Ristorante(String id, String nome, String via,
			Coordinata coordinata) {
		super();
		this.id = id;
		this.nome = nome;
		this.via = via;
		this.coordinata = coordinata;
		this.immagine = "images\\ristoranti\\"+nome+".jpg";
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

	public void setCoordinata(Coordinata coordinata) {
		this.coordinata = coordinata;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

}
