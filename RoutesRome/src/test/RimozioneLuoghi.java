package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Luogo;
import model.Utente;
import model.facade.FacadeLuogo;
import model.facade.FacadeUtente;
import persistence.clusterPoint.LuoghiVisitatiDAOClusterPoint;
import persistence.clusterPoint.LuogoDAOClusterPoint;
import persistence.clusterPoint.QuestionarioDAOClusterPoint;
import persistence.clusterPoint.TagRimossiDAOClusterPoint;

public class RimozioneLuoghi {
	static Utente utente;

	public static void main(String[] args) {
		FacadeUtente facadeUtente = new FacadeUtente();
		FacadeLuogo facadeLuogo = new FacadeLuogo();

		utente = facadeUtente.findUtente("Loris");
		List<Luogo> luoghiVisitabili = facadeLuogo.getLuoghi();
		
		String inizioDB = getOrario();
		luoghiVisitabili = rimuoviUsandoDB(luoghiVisitabili);
		String fineDB = getOrario();
		int durataDB = getTime(fineDB)-getTime(inizioDB);
		
		List<Luogo> luoghiVisitabili2 = facadeLuogo.getLuoghi();
		String inizioLocale = getOrario();
		luoghiVisitabili2 = rimuoviDaLocale(luoghiVisitabili2);
		String fineLocale = getOrario();
		int durataLocale = getTime(fineLocale)-getTime(inizioLocale);
		
		System.out.println();
		System.out.println();
		System.out.println("Report Finale!!!");
		System.out.println("Inizio procedura " + inizioDB + "; Fine procedura " + fineDB +"; Tempo impiegato: " + durataDB);
		System.out.println("Luoghi Trovati tramite metodo DB: " + luoghiVisitabili.size());
		System.out.println();
		System.out.println("Inizio procedura " + inizioLocale + "; Fine procedura " + fineLocale +"; Tempo impiegato: " + durataLocale);
		System.out.println("Luoghi trovati tramite metodo locale: " + luoghiVisitabili2.size());



	}

	private static List<Luogo> rimuoviDaLocale(List<Luogo> luoghiVisitabili) {
		luoghiVisitabili = rimuoviVisitati(luoghiVisitabili);
		luoghiVisitabili = rimuoviSgraditiLocale(luoghiVisitabili);
		luoghiVisitabili = rimuoviQuestionarioLocale(luoghiVisitabili);
		return luoghiVisitabili;
	}

	private static List<Luogo> rimuoviUsandoDB(List<Luogo> luoghiVisitabili) {
		luoghiVisitabili = rimuoviVisitati(luoghiVisitabili);
		luoghiVisitabili = rimuoviSgraditiDB(luoghiVisitabili);
		luoghiVisitabili = rimuoviQuestionarioDB(luoghiVisitabili);
		return luoghiVisitabili;
	}

	// Rimuove luoghi in base al questionario con chiamate al db
	private static List<Luogo> rimuoviQuestionarioDB(List<Luogo> luoghiVisitabili) {
		QuestionarioDAOClusterPoint questionarioDAO = new QuestionarioDAOClusterPoint();
		LuogoDAOClusterPoint luogoDAO = new LuogoDAOClusterPoint();

		Map<String, Integer> preferenze = questionarioDAO.findByID(utente.getUsername()).getPreferenze();
		if (preferenze != null) {
			Set<String> keys = preferenze.keySet();

			for (String key : keys) {
				for (int i = preferenze.get(key); i > 0; i--) {
					List<Luogo> luoghi = luogoDAO.findByCategoria(key, i);

					if (luoghi != null)
						luoghiVisitabili.removeAll(luoghi);
				}
			}
		}
		return luoghiVisitabili;
	}

	// Rimuove i luoghi sgraditi con chiamate al db
	private static List<Luogo> rimuoviSgraditiDB(List<Luogo> luoghiVisitabili) {
		TagRimossiDAOClusterPoint tagRimossiDAO = new TagRimossiDAOClusterPoint();
		LuogoDAOClusterPoint luogoDAO = new LuogoDAOClusterPoint();

		Map<String, Integer> tags = tagRimossiDAO.findByUtente(utente.getUsername());
		Set<String> keys = tags.keySet();

		for (String key : keys) {
			for (int i = tags.get(key); i > 0; i--) {
				List<Luogo> luoghi = luogoDAO.findByCategoria(key, i);

				if (luoghi != null)
					luoghiVisitabili.removeAll(luoghi);
			}
		}
		return luoghiVisitabili;
	}

	// Rimuove secondo il questionario
	private static List<Luogo> rimuoviQuestionarioLocale(List<Luogo> luoghiVisitabili) {
		QuestionarioDAOClusterPoint questionarioDAO = new QuestionarioDAOClusterPoint();
		Map<String, Integer> preferenze = questionarioDAO.findByID(utente.getUsername()).getPreferenze();
		List<Luogo> luoghiDaCanc = new ArrayList<Luogo>();
		
		if(preferenze!=null) {
			for (Luogo luogo : luoghiVisitabili) {
				Map<String, Integer> tagsLuogo = luogo.getTags();
				for (String tipo : tagsLuogo.keySet()) {
					int value = tagsLuogo.get(tipo);
					if (preferenze.containsKey(tipo)) {
						if (value <= preferenze.get(tipo)) {
							luoghiDaCanc.add(luogo);
						}
					}
	
				}
			}
		}

		luoghiVisitabili.removeAll(luoghiDaCanc);

		return luoghiVisitabili;
	}

	// Rimuove i luoghi Sgraditi
	private static List<Luogo> rimuoviSgraditiLocale(List<Luogo> luoghiVisitabili) {
		TagRimossiDAOClusterPoint tagRimossiDAO = new TagRimossiDAOClusterPoint();
		Map<String, Integer> tags = tagRimossiDAO.findByUtente(utente.getUsername());

		List<Luogo> luoghiDaCanc = new ArrayList<Luogo>();

		for (Luogo luogo : luoghiVisitabili) {
			Map<String, Integer> tagsLuogo = luogo.getTags();
			for (String tipo : tagsLuogo.keySet()) {
				int value = tagsLuogo.get(tipo);
				if (tags.containsKey(tipo)) {
					if (value <= tags.get(tipo)) {
						luoghiDaCanc.add(luogo);
					}
				}
			}
		}

		luoghiVisitabili.removeAll(luoghiDaCanc);

		return luoghiVisitabili;
	}

	// Rimuove i luoghi visitati
	private static List<Luogo> rimuoviVisitati(List<Luogo> luoghiVisitabili) {
		LuoghiVisitatiDAOClusterPoint luoghiVisiratiDAO = new LuoghiVisitatiDAOClusterPoint();
		LuogoDAOClusterPoint luogoDAO = new LuogoDAOClusterPoint();

		for (String string : luoghiVisiratiDAO.findByID(utente.getUsername())) {
			Luogo luogo = luogoDAO.getLuogoByID(string);
			luoghiVisitabili.remove(luogo);
		}

		return luoghiVisitabili;
	}
	
	// Ritorna l'ora corrente
	private static String getOrario() {
		Calendar calendar = new GregorianCalendar();	
		int ora = calendar.get(Calendar.HOUR); 
		int minuti = calendar.get(Calendar.MINUTE);;
		int secondi = calendar.get(Calendar.SECOND); 
		
		return ora+":"+minuti+":"+secondi;
	}
	
	private static int getTime(String orario){
		int secondi = Integer.parseInt(orario.split(":", 3)[2]);
		int minuti = Integer.parseInt(orario.split(":", 3)[1]);
		int ora = Integer.parseInt(orario.split(":", 3)[0]);
		
		return (ora*360)+(minuti*60)+(secondi);
	}

}
