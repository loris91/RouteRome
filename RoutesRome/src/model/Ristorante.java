package model;

public class Ristorante extends Item{
	private String immagine;
	
	public Ristorante(String id, String nome, String via,
			Coordinata coordinata) {
		super(id,nome,via,coordinata);
		this.immagine = "images\\ristoranti\\"+nome+".jpg";
	}

	public String getImmagine() {
		return this.immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

}
