<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css\default.css" rel="stylesheet" type="text/css" />
<title>2MSoft-Racommendation - Seleziona Orari</title>
<script src="script/geolocalization.js"></script>
</head>
<body>
	<p align="center">
	<a href="index.jsp"> <img alt="RouteRome - Logo" src="images\copertina.png" width="100%">
	</a></p>
	<form action="raccomanda.do" method="post">
	
		<fieldset>
			<legend>
				<h1>Selezione Orari</h1>
			</legend>
			<p>Ciao ${utente.nome}!</p>
			<p>Seleziona l'orario in cui vuoi iniziare e finire di visitare Roma.</p>
			<h5>
				Inizio: <select name="inizio">
					<option value="null">Seleziona Orario</option>
					<option value="07:00">07:00</option>
					<option value="07:15">07:15</option>
					<option value="07:30">07:30</option>
					<option value="07:45">07:45</option>
					<option value="08:00">08:00</option>
					<option value="08:15">08:15</option>
					<option value="08:30">08:30</option>
					<option value="08:45">08:45</option>
					<option value="09:00">09:00</option>
					<option value="09:15">09:15</option>
					<option value="09:30">09:30</option>
					<option value="09:45">09:45</option>
					<option value="10:00">10:00</option>
					<option value="10:15">10:15</option>
					<option value="10:30">10:30</option>
					<option value="10:45">10:45</option>
					<option value="11:00">11:00</option>
					<option value="11:15">11:15</option>
					<option value="11:30">11:30</option>
					<option value="11:45">11:45</option>
					<option value="12:00">12:00</option>
					<option value="12:15">12:15</option>
					<option value="12:30">12:30</option>
					<option value="12:45">12:45</option>
					<option value="13:00">13:00</option>
					<option value="13:15">13:15</option>
					<option value="13:30">13:30</option>
					<option value="13:45">13:45</option>
					<option value="14:00">14:00</option>
					<option value="14:15">14:15</option>
					<option value="14:30">14:30</option>
					<option value="14:45">14:45</option>
					<option value="15:00">15:00</option>
					<option value="15:15">15:15</option>
					<option value="15:30">15:30</option>
					<option value="15:45">15:45</option>
					<option value="16:00">16:00</option>
					<option value="16:15">16:15</option>
					<option value="16:30">16:30</option>
					<option value="16:45">16:45</option>
					<option value="17:00">17:00</option>
					<option value="17:15">17:15</option>
					<option value="17:30">17:30</option>
					<option value="17:45">17:45</option>
					<option value="18:00">18:00</option>
					<option value="18:15">18:15</option>
					<option value="18:30">18:30</option>
					<option value="18:45">18:45</option>
					<option value="19:00">19:00</option>
					<option value="19:15">19:15</option>
					<option value="19:30">19:30</option>
					<option value="19:45">19:45</option>
					<option value="20:00">20:00</option>
					<option value="20:15">20:15</option>
					<option value="20:30">20:30</option>
					<option value="20:45">20:45</option>
					<option value="21:00">21:00</option>
				</select>
				<c:if test="${erroreInizio!=null}">
   					${erroreInizio}
   				</c:if>
			</h5>
			<h5>
				Fine: <select name="fine">
					<option value="null">Seleziona Orario</option>
					<option value="07:15">07:15</option>
					<option value="07:30">07:30</option>
					<option value="07:45">07:45</option>
					<option value="08:00">08:00</option>
					<option value="08:15">08:15</option>
					<option value="08:30">08:30</option>
					<option value="08:45">08:45</option>
					<option value="09:00">09:00</option>
					<option value="09:15">09:15</option>
					<option value="09:30">09:30</option>
					<option value="09:45">09:45</option>
					<option value="10:00">10:00</option>
					<option value="10:15">10:15</option>
					<option value="10:30">10:30</option>
					<option value="10:45">10:45</option>
					<option value="11:00">11:00</option>
					<option value="11:15">11:15</option>
					<option value="11:30">11:30</option>
					<option value="11:45">11:45</option>
					<option value="12:00">12:00</option>
					<option value="12:15">12:15</option>
					<option value="12:30">12:30</option>
					<option value="12:45">12:45</option>
					<option value="13:00">13:00</option>
					<option value="13:15">13:15</option>
					<option value="13:30">13:30</option>
					<option value="13:45">13:45</option>
					<option value="14:00">14:00</option>
					<option value="14:15">14:15</option>
					<option value="14:30">14:30</option>
					<option value="14:45">14:45</option>
					<option value="15:00">15:00</option>
					<option value="15:15">15:15</option>
					<option value="15:30">15:30</option>
					<option value="15:45">15:45</option>
					<option value="16:00">16:00</option>
					<option value="16:15">16:15</option>
					<option value="16:30">16:30</option>
					<option value="16:45">16:45</option>
					<option value="17:00">17:00</option>
					<option value="17:15">17:15</option>
					<option value="17:30">17:30</option>
					<option value="17:45">17:45</option>
					<option value="18:00">18:00</option>
					<option value="18:15">18:15</option>
					<option value="18:30">18:30</option>
					<option value="18:45">18:45</option>
					<option value="19:00">19:00</option>
					<option value="19:15">19:15</option>
					<option value="19:30">19:30</option>
					<option value="19:45">19:45</option>
					<option value="20:00">20:00</option>
					<option value="20:15">20:15</option>
					<option value="20:30">20:30</option>
					<option value="20:45">20:45</option>
					<option value="21:00">21:00</option>
				</select>
				<c:if test="${erroreFine!=null}">
   					${erroreFine}
   				</c:if>
			</h5>
			
			<p>Inserisci un indirizzo o fatti geolocalizzare e poi conferma!</p>
			<h5>
				<input id="idText" type="text" placeholder="Punto di partenza" name="posizione" />
				<c:if test="${erroreIndirizzo!=null}">
   					${erroreIndirizzo}
   				</c:if>
			</h5>
			<input type="submit" value="Conferma!" name="bottone" />
			
			<input type="button" onclick="getLocation();" value="Get Location" />			


		</fieldset>
	</form>
	<%@ include file="diritti.jsp" %>
</body>
</html>