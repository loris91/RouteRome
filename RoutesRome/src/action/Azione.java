package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Azione {

	public abstract String esegui(HttpServletRequest request,
			HttpServletResponse response);
	
}
