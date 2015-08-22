<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css\default.css" rel="stylesheet" type="text/css" />
<link href="css\cssbarra.css" rel="stylesheet" type="text/css" />
<style>
#map-canvas {
	width: 600px;
	height: 500px;
}
</style>
<script src="https://maps.googleapis.com/maps/api/js"></script>
<title>RouteRome - Mappa</title>
</head>
<body>
	<p align="center">
		<a href="index.jsp"> <img alt="RouteRome - Logo"
			src="images\copertina.png" width="100%">
		</a>
	</p>
	<fieldset>
		<legend>
			<h3>Itinerario Proposto</h3>
		</legend>


		<script type="text/javascript">
				function initialize() {
					var path = '${mete}';
					var json = JSON.stringify(eval('(' + path + ')'));
					json = JSON.parse(json);
					var lat1 = json[0].coordinata.latitudine;
					var lon1 = json[0].coordinata.longitudine;

					var mapCanvas = document.getElementById('map-canvas');

					var mapOptions = {
						center : new google.maps.LatLng(lat1, lon1),
						zoom : 15,
						mapTypeId : google.maps.MapTypeId.ROADMAP
					}
					var map = new google.maps.Map(mapCanvas, mapOptions)
					var iconBase = 'http://maps.google.com/mapfiles/kml/paddle/';

					for (var i = 0; i < json.length; i++) {
						var lat = json[i].coordinata.latitudine;
						var lon = json[i].coordinata.longitudine;
						var nome = json[i].nome;
						var place = new google.maps.LatLng(lat, lon);
						var marker = new google.maps.Marker({
							position : place,
							map : map,
							icon : iconBase + (i + 1) + '.png',
							title : nome
						});
					}
				}
				google.maps.event.addDomListener(window, 'load', initialize);
			</script>

		<div id="map-canvas" align="center"></div>
		<form action="modifica.do" method="post">
			<c:set var="count" scope="page" value="${0}" />
			<table style="width: 100%">
				<c:forEach var="item" items="${itinerario}">
					<tr>
						<td><img alt="${item.nome }" src="${item.immagine }"></td>
						<td>${item.nome }</td>
						<td>${item.via }</td>
						<td><input type="checkbox" name="sceltaLuoghi"
							value="${count }" /> Rimuovi</td>
					</tr>
					<c:set var="count" value="${count + 1}" scope="page" />
				</c:forEach>
			</table>

			<input type="submit" value="ModificaItinerario" name="bottone" />
		</form>
		<form action="conferma.do" method="post">
			<input type="submit" value="ConfermaItinerario" name="bottone" />
		</form>

	</fieldset>
	<%@ include file="diritti.jsp"%>
</body>
</html>