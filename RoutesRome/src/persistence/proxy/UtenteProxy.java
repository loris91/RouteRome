package persistence.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Luogo;
import model.Utente;
import model.facade.FacadeLuoghiVisitati;
import model.facade.FacadeLuogo;
import model.facade.FacadeQuestionario;
import model.facade.FacadeTagRimossi;
import persistence.clusterPoint.LuogoDAOClusterPoint;

public class UtenteProxy extends Utente {
	private boolean caricato = false;

	public List<Luogo> getLuoghiVisitabili() {
		if (!this.caricato) {
			List<Luogo> luoghiVisitabili = new ArrayList<Luogo>();
			LuogoDAOClusterPoint dao = new LuogoDAOClusterPoint();
			luoghiVisitabili = filtraLuoghi(dao.findAll());
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
		FacadeLuoghiVisitati facadeLuoghiVisitati = new FacadeLuoghiVisitati();
		FacadeLuogo facadeLuogo = new FacadeLuogo();

		for (String string : facadeLuoghiVisitati.getLuoghiVisitati(this.username)) {
			Luogo luogo = facadeLuogo.getLuogoById(string);
			luoghiVisitabili.remove(luogo);
		}
		return luoghiVisitabili;
	}

	
	//Elimina dai luoghi vititabili quelli che l'utente ha scartato dalla raccomandazione
	private List<Luogo> rimuoviSgraditi(List<Luogo> luoghiVisitabili) {
		FacadeTagRimossi facadeTagRimossi = new FacadeTagRimossi();
		FacadeLuogo facadeLuogo = new FacadeLuogo();

		Map<String, Integer> tags = facadeTagRimossi.getTagRimossi(this.username);
		Set<String> keys = tags.keySet();

		for (String key : keys) {
			for (int i = tags.get(key); i > 0; i--) {
				List<Luogo> luoghi = facadeLuogo.getLuogoByCategoria(key, i);

				if (luoghi != null)
					luoghiVisitabili.removeAll(luoghi);
			}
		}
		return luoghiVisitabili;
	}

	// Filtra i luoghi visitabili in base alle preferenze selezionate nel questionario
	private List<Luogo> rimuoviQuestionario(List<Luogo> luoghiVisitabili) {
		FacadeQuestionario facadeQuestionario = new FacadeQuestionario();
		FacadeLuogo facadeLuogo = new FacadeLuogo();

		Map<String, Integer> preferenze = facadeQuestionario.getQuestionarioByID(super.username).getPreferenze();
		if (preferenze != null) {
			Set<String> keys = preferenze.keySet();

			for (String key : keys) {
				for (int i = preferenze.get(key); i > 0; i--) {
					List<Luogo> luoghi = facadeLuogo.getLuogoByCategoria(key, i);

					if (luoghi != null)
						luoghiVisitabili.removeAll(luoghi);
				}
			}
		}

		return luoghiVisitabili;
	}

}
