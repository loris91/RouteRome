package persistence.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Luogo;
import model.Utente;
import persistence.clusterPoint.LuoghiVisitatiDAOClusterPoint;
import persistence.clusterPoint.LuogoDAOClusterPoint;
import persistence.clusterPoint.QuestionarioDAOClusterPoint;
import persistence.clusterPoint.TagRimossiDAOClusterPoint;

public class UtenteProxy extends Utente {
	private boolean caricato = false;

	public List<Luogo> getLuoghiVisitabili() {
		if (!this.caricato) {
			List<Luogo> luoghiVisitabili = new ArrayList<Luogo>();
			LuogoDAOClusterPoint luogoDao = new LuogoDAOClusterPoint();
			luoghiVisitabili = filtraLuoghi(luogoDao.findAll());
			this.setLuoghiVisitabili(luoghiVisitabili);
			return luoghiVisitabili;
		}
		return super.getLuoghiVisitabili();
	}

	// Filtraggio della lista secondo i gusti
	private List<Luogo> filtraLuoghi(List<Luogo> luoghi) {
		luoghi = this.rimuoviVisitati(luoghi);
		luoghi = this.rimuoviSgraditi(luoghi);
		luoghi = this.rimuoviQuestionario(luoghi);
		return luoghi;
	}

	// Elimina dai luoghi visitabili quelli che l'utente ha gia visitato
	private List<Luogo> rimuoviVisitati(List<Luogo> luoghiVisitabili) {
		LuoghiVisitatiDAOClusterPoint luoghiVisiratiDAO = new LuoghiVisitatiDAOClusterPoint();
		LuogoDAOClusterPoint luogoDAO = new LuogoDAOClusterPoint();

		for (String string : luoghiVisiratiDAO.findByID(super.username)) {
			Luogo luogo = luogoDAO.getLuogoByID(string);
			luoghiVisitabili.remove(luogo);
		}

		return luoghiVisitabili;
	}

	// Elimina dai luoghi vititabili quelli che l'utente ha scartato dalla
	// raccomandazione
	private List<Luogo> rimuoviSgraditi(List<Luogo> luoghiVisitabili) {
		TagRimossiDAOClusterPoint tagRimossiDAO = new TagRimossiDAOClusterPoint();
		Map<String, Integer> tags = tagRimossiDAO.findByUtente(super.username);

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

	// Filtra i luoghi visitabili in base alle preferenze selezionate nel
	// questionario
	private List<Luogo> rimuoviQuestionario(List<Luogo> luoghiVisitabili) {
		QuestionarioDAOClusterPoint questionarioDAO = new QuestionarioDAOClusterPoint();
		Map<String, Integer> preferenze = questionarioDAO.findByID(super.username).getPreferenze();
		List<Luogo> luoghiDaCanc = new ArrayList<Luogo>();

		System.out.println(super.username.toString());
		if (preferenze != null) {
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

}
