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
	
	public float distFrom(Coordinata coordinata) {
		Double latitudine = coordinata.getLatitudine();
		Double longitudine = coordinata.getLongitudine();
		
		double earthRadius = 6371000; // meters
		double dLat = Math.toRadians(latitudine - this.latitudine);
		double dLng = Math.toRadians(longitudine - this.longitudine);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(this.latitudine))
				* Math.cos(Math.toRadians(latitudine)) * Math.sin(dLng / 2)
				* Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (earthRadius * c);

		return dist;
	} 

}
