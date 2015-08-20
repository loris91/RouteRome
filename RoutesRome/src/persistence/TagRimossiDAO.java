package persistence;

import java.util.Map;

public interface TagRimossiDAO {
	
	boolean insert(String idUtente, String tag, int rate);
		
	Map<String, Integer> findByUtente(String idUtente);

}