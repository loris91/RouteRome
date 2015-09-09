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
				arena = "3";
			if (castello==null)
				castello = "3";
			if (chiesa==null)
				chiesa = "3";
			if (cimitero==null)
				cimitero = "3";
			if (edificio==null)
				edificio = "3";
			if (fontana==null)
				fontana = "3";
			if (giardino==null)
				giardino = "3";
			if (monumento==null)
				monumento = "3";
			if (marte==null)
				marte = "3";
			if (msettore==null)
				msettore = "3";
			if (mstorico==null)
				mstorico = "3";
			if (palazzo==null)
				palazzo = "3";
			if (parco==null)
				parco = "3";
			if (passeggiata==null)
				passeggiata = "3";
			if (piazza==null)
				piazza = "3";
			if (ponte==null)
				ponte = "3";
			if (rovine==null)
				rovine = "3";
			if (sreligioso==null)
				sreligioso = "3";
			if (sstorico==null)
				sstorico = "3";
			if (villa==null)
				villa = "3";
			
			preferenze.put("Arena", Integer.parseInt(arena));
			preferenze.put("Castello", Integer.parseInt(castello));
			preferenze.put("Chiesa", Integer.parseInt(chiesa));
			preferenze.put("Cimitero", Integer.parseInt(cimitero));
			preferenze.put("EdificioArchitettonico", Integer.parseInt(edificio));
			preferenze.put("Fontana", Integer.parseInt(fontana));
			preferenze.put("Giardino", Integer.parseInt(giardino));
			preferenze.put("Monumento", Integer.parseInt(monumento));
			preferenze.put("MuseoArte", Integer.parseInt(marte));
			preferenze.put("MuseoSettore", Integer.parseInt(msettore));
			preferenze.put("MuseoStorico", Integer.parseInt(mstorico));
			preferenze.put("Palazzo", Integer.parseInt(palazzo));
			preferenze.put("ParchiNatura", Integer.parseInt(parco));
			preferenze.put("PasseggiateSitoStorico", Integer.parseInt(passeggiata));
			preferenze.put("Piazza", Integer.parseInt(piazza));
			preferenze.put("Ponte", Integer.parseInt(ponte));
			preferenze.put("RovineAntiche", Integer.parseInt(rovine));
			preferenze.put("SitoReligioso", Integer.parseInt(sreligioso));
			preferenze.put("SitoStorico", Integer.parseInt(sstorico));
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