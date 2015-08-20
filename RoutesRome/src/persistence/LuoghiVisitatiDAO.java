package persistence;

import java.util.List;

public interface LuoghiVisitatiDAO {
	
	boolean insert(String idUtente,List<String> luoghiRaccomandati);
	
	List<String> luoghiVisitati(String idUtente);

}
