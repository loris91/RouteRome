package model;
public class Coordinata {
	private Double latitudine;
	private Double longitudine;
	
	public Coordinata(Double latitudine, Double longitudine) {
		super();
		this.latitudine = latitudine;
		this.longitudine = longitudine;
	}
	public Double getLatitudine() {
		return latitudine;
	}
	public Double getLongitudine() {
		return longitudine;
	}	

}
