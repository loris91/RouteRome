package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;
import model.facade.FacadeQuestionario;

public class AzioneConfermaQuestionario extends Azione {

	@Override
	public String esegui(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		FacadeQuestionario facadeQuestionario = new FacadeQuestionario();
		
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

		

		utente.setIncompilato(false);
		return "compilazionePositiva";		
	}

}
