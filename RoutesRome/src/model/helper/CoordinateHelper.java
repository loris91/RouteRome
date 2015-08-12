package model.helper;
import model.Coordinata;
import it.elbuild.jcoord.LatLng;
import it.elbuild.jcoord.resolver.GeoCodeResolver;

public class CoordinateHelper {
	
	public CoordinateHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coordinata getCoordinate(String via) {
		//inizializzo le variabili statiche
		String provincia = "Roma";
		String comune = "Roma";
		LatLng coord = null;
		// richiamo un metodo statico di JCooord che ritorna le coordinate
		do{
			coord = getCoordinate(provincia,comune, via);
		} while (coord == null);
		
		
		Double latitudine = coord.getLat().doubleValue();
		Double longitudine = coord.getLng().doubleValue();
		Coordinata coordinata = new Coordinata(latitudine, longitudine);
		
		return coordinata;
	}

	private LatLng getCoordinate(String provincia, String comune, String via) {
		LatLng coord = GeoCodeResolver.findExactCoordForAddress(provincia,comune, via); 
		return coord;
	}
	
}