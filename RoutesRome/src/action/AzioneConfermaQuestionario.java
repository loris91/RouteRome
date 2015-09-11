package action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Questionario;
import model.Utente;
import model.facade.FacadeQuestionario;

public class AzioneConfermaQuestionario extends Azione {

	@Override
	public String esegui(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			Utente utente = (Utente) session.getAttribute("utente");
			FacadeQuestionario facadeQuestionario = new FacadeQuestionario();
			
			Map<String, Integer> preferenze = new HashMap<String, Integer>();
			
			
			String arena=(String)request.getParameter("arena");
			String castello=(String)request.getParameter("castello");
			String chiesa=(String)request.getParameter("chiesa");
			String cimitero=(String)request.getParameter("cimitero");
			String edificio=(String)request.getParameter("edificio");
			String fontana=(String)request.getParameter("fontana");
			String giardino=(String)request.getParameter("giardino");
			String monumento=(String)request.getParameter("monumento");
			String marte=(String)request.getParameter("marte");
			String msettore=(String)request.getParameter("msettore");
			String mstorico=(String)request.getParameter("mstorico");
			String palazzo=(String)request.getParameter("palazzo");
			String parco=(String)request.getParameter("parco");
			String passeggiata=(String)request.getParameter("passeggiata");
			String piazza=(String)request.getParameter("piazza");
			String ponte=(String)request.getParameter("ponte");
			String rovine=(String)request.getParameter("rovine");
			String sreligioso=(String)request.getParameter("sreligioso");
			String sstorico=(String)request.getParameter("sstorico");
			String villa=(String)request.getParameter("villa");
			
			if (arena==null)
				arena = "0";
			if (castello==null)
				castello = "0";
			if (chiesa==null)
				chiesa = "0";
			if (cimitero==null)
				cimitero = "0";
			if (edificio==null)
				edificio = "0";
			if (fontana==null)
				fontana = "0";
			if (giardino==null)
				giardino = "0";
			if (monumento==null)
				monumento = "0";
			if (marte==null)
				marte = "0";
			if (msettore==null)
				msettore = "0";
			if (mstorico==null)
				mstorico = "0";
			if (palazzo==null)
				palazzo = "0";
			if (parco==null)
				parco = "0";
			if (passeggiata==null)
				passeggiata = "0";
			if (piazza==null)
				piazza = "0";
			if (ponte==null)
				ponte = "0";
			if (rovine==null)
				rovine = "0";
			if (sreligioso==null)
				sreligioso = "0";
			if (sstorico==null)
				sstorico = "0";
			if (villa==null)
				villa = "0";
			
			preferenze.put("Arena", Integer.parseInt(arena));
			preferenze.put("Castello", Integer.parseInt(castello));
			preferenze.put("Chiesa", Integer.parseInt(chiesa));
			preferenze.put("Cimitero", Integer.parseInt(cimitero));
			preferenze.put("Edificio Architettonico", Integer.parseInt(edificio));
			preferenze.put("Fontana", Integer.parseInt(fontana));
			preferenze.put("Giardino", Integer.parseInt(giardino));
			preferenze.put("Monumento", Integer.parseInt(monumento));
			preferenze.put("Museo d'Arte", Integer.parseInt(marte));
			preferenze.put("Museo di Settore", Integer.parseInt(msettore));
			preferenze.put("Museo Storico", Integer.parseInt(mstorico));
			preferenze.put("Palazzo", Integer.parseInt(palazzo));
			preferenze.put("Parchi e Natura", Integer.parseInt(parco));
			preferenze.put("Passeggiate in Sito Storico", Integer.parseInt(passeggiata));
			preferenze.put("Piazza", Integer.parseInt(piazza));
			preferenze.put("Ponte", Integer.parseInt(ponte));
			preferenze.put("Rovine Antiche", Integer.parseInt(rovine));
			preferenze.put("Sito Religioso", Integer.parseInt(sreligioso));
			preferenze.put("Sito Storico", Integer.parseInt(sstorico));
			preferenze.put("Villa", Integer.parseInt(villa));

			Questionario questionario = new Questionario(utente.getUsername(), preferenze);
			if (facadeQuestionario.addQuestionario(questionario)) {
				utente.setIncompilato(false);
				return "compilazionePositiva";	
			} else {
				return "compilazioneNegativa";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore nella conferma del questionario");
			return "errore";
		}
	}
}