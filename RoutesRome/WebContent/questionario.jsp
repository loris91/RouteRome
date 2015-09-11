<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css\default.css" rel="stylesheet" type="text/css" />
<title>RouteRome - Questionario</title>
<script src="script/geolocalization.js"></script>
</head>
<body>
	<p align="center">
	<a href="index.jsp"> <img alt="RouteRome - Logo" src="images\copertina.png" width="100%">
	</a></p>
	
	
		<fieldset>
			<legend>
				<h3>Questionario</h3>
			</legend>	
			<p>Compila il questionario per poterci aiutare a personalizzare il sistema e per raccomandarti i luoghi piu' adatti per te!</p>
			
			<form action="questionario.do" method="post">
				<table style="width: 100%">
					<tr><td>Arena</td><td>
						1<input type="radio" name="arena" value="2"/>
						2 <input type="radio" name="arena" value="1"/>
						3<input type="radio" name="arena" value="0"/>
	    			</td></tr>
	    			<tr><td>Castello</td><td>
						1<input type="radio" name="castello" value="2"/>
						2 <input type="radio" name="castello" value="1"/>
						3<input type="radio" name="castello" value="0"/>
	    			</td></tr>
	    			<tr><td>Chiesa</td><td>
						1<input type="radio" name="chiesa" value="2"/>
						2 <input type="radio" name="chiesa" value="1"/>
						3<input type="radio" name="chiesa" value="0"/>
	    			</td></tr>
	    			<tr><td>Cimitero</td><td>
						1<input type="radio" name="cimitero" value="2"/>
						2 <input type="radio" name="cimitero" value="1"/>
						3<input type="radio" name="cimitero" value="0"/>
	    			</td></tr>
	    			<tr><td>Edificio Architettonico</td><td>
						1<input type="radio" name="edificio" value="2"/>
						2 <input type="radio" name="edificio" value="1"/>
						3<input type="radio" name="edificio" value="0"/>
	    			</td></tr>
	    			<tr><td>Fontana</td><td>
						1<input type="radio" name="fontana" value="2"/>
						2 <input type="radio" name="fontana" value="1"/>
						3<input type="radio" name="fontana" value="0"/>
	    			</td></tr>
	    			<tr><td>Giardino</td><td>
						1<input type="radio" name="giardino" value="2"/>
						2 <input type="radio" name="giardino" value="1"/>
						3<input type="radio" name="giardino" value="0"/>
	    			</td></tr>
	    			<tr><td>Monumento</td><td>
						1<input type="radio" name="monumento" value="2"/>
						2 <input type="radio" name="monumento" value="1"/>
						3<input type="radio" name="monumento" value="0"/>
	    			</td></tr>
	    			<tr><td>Museo d'Arte</td><td>
						1<input type="radio" name="marte" value="2"/>
						2 <input type="radio" name="marte" value="1"/>
						3<input type="radio" name="marte" value="0"/>
	    			</td></tr>
	    			<tr><td>Museo di Settore</td><td>
						1<input type="radio" name="msettore" value="2"/>
						2 <input type="radio" name="msettore" value="1"/>
						3<input type="radio" name="msettore" value="0"/>
	    			</td></tr>
	    			<tr><td>Museo Storico</td><td>
						1<input type="radio" name="mstorico" value="2"/>
						2 <input type="radio" name="mstorico" value="1"/>
						3<input type="radio" name="mstorico" value="0"/>
	    			</td></tr>
	    			<tr><td>Palazzo</td><td>
						1<input type="radio" name="palazzo" value="2"/>
						2 <input type="radio" name="palazzo" value="1"/>
						3<input type="radio" name="palazzo" value="0"/>
	    			</td></tr>
	    			<tr><td>Parchi e Natura</td><td>
						1<input type="radio" name="parco" value="2"/>
						2 <input type="radio" name="parco" value="1"/>
						3<input type="radio" name="parco" value="0"/>
	    			</td></tr>
	    			<tr><td>Passeggiate in Sito Storico</td><td>
						1<input type="radio" name="passeggiata" value="2"/>
						2 <input type="radio" name="passeggiata" value="1"/>
						3<input type="radio" name="passeggiata" value="0"/>
	    			</td></tr>
	    			<tr><td>Piazza</td><td>
						1<input type="radio" name="piazza" value="2"/>
						2 <input type="radio" name="piazza" value="1"/>
						3<input type="radio" name="piazza" value="0"/>
	    			</td></tr>
	    			<tr><td>Ponte</td><td>
						1<input type="radio" name="ponte" value="2"/>
						2 <input type="radio" name="ponte" value="1"/>
						3<input type="radio" name="ponte" value="0"/>
	    			</td></tr>
	    			<tr><td>Rovine Antiche</td><td>
						1<input type="radio" name="rovine" value="2"/>
						2 <input type="radio" name="rovine" value="1"/>
						3<input type="radio" name="rovine" value="0"/>
	    			</td></tr>
	    			<tr><td>Sito Religioso</td><td>
						1<input type="radio" name="sreligioso" value="2"/>
						2 <input type="radio" name="sreligioso" value="1"/>
						3<input type="radio" name="sreligioso" value="0"/>
	    			</td></tr>
	    			<tr><td>Sito Storico</td><td>
						1<input type="radio" name="sstorico" value="2"/>
						2 <input type="radio" name="sstorico" value="1"/>
						3<input type="radio" name="sstorico" value="0"/>
	    			</td></tr>
	    			<tr><td>Villa</td><td>
						1<input type="radio" name="villa" value="2"/>
						2 <input type="radio" name="villa" value="1"/>
						3<input type="radio" name="villa" value="0"/>
	    			</td></tr>			
				</table>
				<br>
				<input type="submit" value="Conferma!" name="bottone" />
			</form>

		</fieldset>
	</form>
	<%@ include file="diritti.jsp" %>
</body>
</html>