package model.helper;

import java.util.Map;
import java.util.Set;

import model.Luogo;
import model.facade.FacadeTagRimossi;

public class LuoghiSgraditiHelper {

	public LuoghiSgraditiHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addTags(Luogo luogo, String idUtente) {
		FacadeTagRimossi facadeTagRimossi = new FacadeTagRimossi();
		Map<String, Integer> tagLuogo = luogo.getTags();
		Map<String, Integer> tagRimossi = facadeTagRimossi.getTagRimossi(idUtente);

		Set<String> keys = tagLuogo.keySet();

		for (String key : keys) {
			Integer value = tagLuogo.get(key);
			
			if (tagRimossi.containsKey(key)) {
				Integer dbValue = tagRimossi.get(key);
				if (value > dbValue) {
					facadeTagRimossi.addTagRimosso(idUtente, key, value);
				}
			} else {
				facadeTagRimossi.addTagRimosso(idUtente, key, value);
			}
		}

	}

}
