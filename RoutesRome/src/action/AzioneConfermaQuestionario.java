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
			
			
			String musei=(String)request.getParameter("musei");
			String chiesa=(String)request.getParameter("chiesa");
			String sitiStorici=(String)request.getParameter("sitiStorici");
			String arene=(String)request.getParameter("arena");
			String edificiArchitettonici=(String)request.getParameter("edifici");
			String sitiReligiosi=(String)request.getParameter("sitiReligiosi");
			String villa=(String)request.getParameter("villa");
			
			if (musei==null)
				musei = "3";
			if (chiesa==null)
				chiesa = "3";
			if (sitiStorici==null)
				sitiStorici = "3";
			if (arene==null)
				arene = "3";
			if (edificiArchitettonici==null)
				edificiArchitettonici = "3";
			if (sitiReligiosi==null)
				sitiReligiosi = "3";
			if (villa==null)
				villa = "3";
			
			preferenze.put("Musei", Integer.parseInt(musei));
			preferenze.put("Chiesa", Integer.parseInt(chiesa));
			preferenze.put("SitiStorici", Integer.parseInt(sitiStorici));
			preferenze.put("Arene", Integer.parseInt(arene));
			preferenze.put("EdificiArchitettonici", Integer.parseInt(edificiArchitettonici));
			preferenze.put("SitiReligiosi", Integer.parseInt(sitiReligiosi));
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