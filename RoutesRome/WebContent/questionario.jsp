<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
			<p>Compila il questionario per poterci aiutare a personalizzare il sistema e per raccomandarti i luoghi più adatti per te!</p>
			
			<form action="questionario.do" method="post">
				<table style="width: 100%">
					<tr>
						<td>Musei</td>
						<td>
						        Fa schifo <input type="radio" name="musei" value="1"/>
						        Gnhé <input type="radio" name="musei" value="2"/>
						        Troppo togo! <input type="radio" name="musei" value="3"/>
	    				</td>
					</tr>
					<tr>
						<td>Chiesa</td>
						<td>
						        Fa Schifo <input type="radio" name="chiesa" value="1"/>
						        Gnhé <input type="radio" name="chiesa" value="2"/>
						        Troppo togo! <input type="radio" name="chiesa" value="3"/>
	    				</td>
					</tr>
					<tr>
						<td>Siti Storici</td>
						<td>
						        Fa Schifo <input type="radio" name="sitiStorici" value="1"/>
						        Gnhé <input type="radio" name="sitiStorici" value="2"/>
						        Troppo togo! <input type="radio" name="sitiStorici" value="3"/>
	    				</td>
					</tr>
					<tr>
						<td>Arene</td>
						<td>
						        Fa Schifo <input type="radio" name="arena" value="1"/>
						        Gnhé <input type="radio" name="arena" value="2"/>
						        Troppo togo! <input type="radio" name="arena" value="3"/>
	    				</td>
					</tr>
					<tr>
						<td>Edifici Architettonici</td>
						<td>
						        Fa Schifo <input type="radio" name="edifici" value="1"/>
						        Gnhé <input type="radio" name="edifici" value="2"/>
						        Troppo togo! <input type="radio" name="edifici" value="3"/>
	    				</td>
					</tr>
					<tr>
						<td>Siti Religiosi</td>
						<td>
						        Fa Schifo <input type="radio" name="sitiReligiosi" value="1"/>
						        Gnhé <input type="radio" name="sitiReligiosi" value="2"/>
						        Troppo togo! <input type="radio" name="sitiReligiosi" value="3"/>
	    				</td>
					</tr>
					<tr>
						<td>Villa</td>
						<td>
						        Fa Schifo <input type="radio" name="villa" value="1"/>
						        Gnhé <input type="radio" name="villa" value="2"/>
						        Troppo togo! <input type="radio" name="villa" value="3"/>
	    				</td>
					</tr>
				</table>
				<br>
				<input type="submit" value="Conferma!" name="bottone" />
			</form>

		</fieldset>
	</form>
	<%@ include file="diritti.jsp" %>
</body>
</html>