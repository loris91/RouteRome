package test;

import model.facade.FacadeQuestionario;

public class Main {

	public static void main(String[] args) {

		FacadeQuestionario facade = new FacadeQuestionario();

//		String id = "Frappo";
//		Map<String, Integer> preferenze = new HashMap<String, Integer>();
//		preferenze.put("Musei", 3);
//		Questionario questionario = new Questionario(id, preferenze);
//		facade.addQuestionario(questionario);
		
		System.out.println(facade.getQuestionarioByID("a").toString());
	}

}
