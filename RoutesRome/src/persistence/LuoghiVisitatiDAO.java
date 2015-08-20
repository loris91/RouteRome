package persistence;

import java.util.List;

public interface LuoghiVisitatiDAO {
	
	boolean insert(String idUtente,List luoghiRaccomandati);
	
	List<String> luoghiVisitati(String idUtente);

}
