package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;

public class AzioneConfermaQuestionario extends Azione {

	@Override
	public String esegui(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		
		String musei=(String)request.getParameter("musei");
		String chiesa=(String)request.getParameter("chiesa");
		String sitiStorici=(String)request.getParameter("sitiStorici");
		String arene=(String)request.getParameter("arena");
		String edificiArchitettonici=(String)request.getParameter("edifici");
		String sitiReligiosi=(String)request.getParameter("sitiReligiosi");
		String villa=(String)request.getParameter("villa");
		
		System.out.println("Musei: " + musei);
		System.out.println("Chiesa: " + chiesa);
		System.out.println("Siti Storici:" + sitiStorici);
		System.out.println("Arene: " + arene);
		System.out.println("Edifici Architettonici: " + edificiArchitettonici);
		System.out.println("Siti Religiosi: " + sitiReligiosi);
		System.out.println("Villa: " + villa);
		
		
		
		
		
		return "compilazionePositiva";		
	}

}
