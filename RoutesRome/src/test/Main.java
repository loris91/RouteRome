package test;

import java.util.Map;
import java.util.Set;

import model.Questionario;
import model.facade.FacadeQuestionario;

public class Main {

	public static void main(String[] args) {

		FacadeQuestionario facade = new FacadeQuestionario();

		// String id = "Frappo";
		// Map<String, Integer> preferenze = new HashMap<String, Integer>();
		// preferenze.put("Musei", 3);
		// Questionario questionario = new Questionario(id, preferenze);
		// facade.addQuestionario(questionario);

		Questionario questionario = facade.getQuestionarioByID("test");

		if (questionario == null) {
			System.out.println("QUESTIONARIO NULL");
		} else {
			questionario.toString();
			Map<String, Integer> preferenze = questionario.getPreferenze();

			if (preferenze == null) {
				System.out.println("PREFERENZE NULL");
			} else {
				Set<String> keys = preferenze.keySet();
				for (String key : keys) {
					System.out.println(preferenze.get(key));
				}
			}
		}
	}

}
