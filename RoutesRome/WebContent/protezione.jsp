<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Utente;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		HttpSession sessione = request.getSession();
		Utente utente = ((Utente) sessione.getAttribute("utente"));
		if (utente == null) {
			out.clear();
			request.setAttribute("errore",
					"Azione non disponibile,non sei autenticato!");
			RequestDispatcher rd = application
					.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}
	%>

</body>
</html>