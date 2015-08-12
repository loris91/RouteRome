<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css\default.css" rel="stylesheet" type="text/css" />
<title>2MSoft-Racommendation - Registrazione</title>
</head>
<body>
	<p align="center">
	<a href="index.jsp"> <img alt="RouteRome - Logo" src="images\copertina.png" width="100%">
	</a></p>
	<form action="registra.do" method="post">

		<ul>
			<c:if test="${errorePasswordDiverse!=null}">
   				${errorePasswordDiverse}
   			</c:if>
		</ul>
		<ul>
			<c:if test="${erroreEmailDiverse!=null}">
   				${erroreEmailDiverse}
   			</c:if>
		</ul>
		<fieldset>
			<legend>
				<h1>Registrazione</h1>
			</legend>
			Ciao! Inserisci i dati richiesti per attivare il tuo account!
			<h5>
				Username: <input type="text" placeholder="username" name="username"
					value="<%=(request.getParameter("username") != null) ? request
					.getParameter("username") : ""%>" />
				<c:if test="${erroreUsername!=null}">
   					${erroreUsername}
   				</c:if>
			</h5>
			<h5>
				Password: <input type="password" placeholder="password"
					name="password" />
				<c:if test="${errorePassword!=null}">
   					${errorePassword}
   				</c:if>
			</h5>
			<h5>
				Inserisci di nuovo per confermare: <input type="password"
					placeholder="password" name="passwordConferma" />
				<c:if test="${errorePassword!=null}">
   					${errorePassword}
   				</c:if>
			</h5>
			<h5>
				e-mail: <input type="text" placeholder="e-mail" name="email" />
				<c:if test="${erroreEmail!=null}">
   					${erroreEmail}
   				</c:if>
			</h5>
			<h5>
				Inserisci di nuovo per confermare: <input type="text"
					placeholder="e-mail" name="emailConferma" />
				<c:if test="${erroreEmail!=null}">
   					${erroreEmail}
   				</c:if>
			</h5>
			<h5>
				Nome: <input type="text" placeholder="nome" name="nome"
					value="<%=(request.getParameter("nome") != null) ? request
					.getParameter("nome") : ""%>" />
				<c:if test="${erroreNome!=null}">
   					${erroreNome}
   				</c:if>
			</h5>
			<h5>
				Cognome: <input type="text" placeholder="cognome" name="cognome"
					value="<%=(request.getParameter("cognome") != null) ? request
					.getParameter("cognome") : ""%>" />
				<c:if test="${erroreCognome!=null}">
   					${erroreCognome}
   				</c:if>
			</h5>

			
			<input type="submit" value="Crea account" name="bottone" />
			
		</fieldset>
	</form>
	<%@ include file="diritti.jsp" %>
</body>
</html>