<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@page import="com.itextpdf.text.Document"%>
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

		<table style="width: 100%">
			<c:forEach var="item" items="${itinerario}">
				<tr>
					<td><img alt="${item.nome }" src="${item.immagine }"></td>
					<td>${item.nome }</td>
					<td>${item.via }</td>
				</tr>
			</c:forEach>
		</table>

	</fieldset>
	</form>
	<%@ include file="diritti.jsp"%>
</body>
</html>