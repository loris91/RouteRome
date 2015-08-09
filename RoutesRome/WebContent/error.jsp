<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css\default.css" rel="stylesheet" type="text/css" />
<title>3MSoft - Error Page</title>
</head>
<body>
	<fieldset style="border-color: lime;">
		<legend>
			<h1> Errore!</h1>
		</legend>
		<div style="text-align: center;">
		
		<h2>Un Pokemon ha attaccato i nostri server!!!!</h2>
		<h2>Presto chiama l'agente Jenny!!!!!!!!!!!!!!!!!!!</h2>
		<img alt="server Attack" src="images\poke.jpg">
		<br>
		<br>
		<font size="3">Dettagli errore:</font>
		<p>${errore }</p>
	</div>
	</fieldset>
	<%@ include file="diritti.jsp" %>
</body>
</html>