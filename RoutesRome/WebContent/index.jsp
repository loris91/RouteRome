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
<title>2MSoft-Racommendation - Home Page</title>
</head>
<body>
	<p align="center">
	<a href="index.jsp"> <img alt="RouteRome - Logo" src="images\copertina.png" width="100%">
	</a></p>
	<form action="login.do" method="post">
		<fieldset>
			<legend>
				<h1>Home Page</h1>
			</legend>

			<div style="text-align: center;">

				<p>Accedi per poter usufruire dei nostri servizi!</p>


				<h5>
					<input type="text" placeholder="username" name="username"
						value="<%=(request.getAttribute("username") != null) ? request
					.getAttribute("username") : ""%>" />
					<c:if test="${erroreUsername!=null}">
   					${erroreUsername}
   				</c:if>
				</h5>

				<h5>
					<input type="password" placeholder="password" name="password" />
					<c:if test="${errorePassword!=null}">
   					${errorePassword}
   				</c:if>
				</h5>
				<p>${erroreAccesso}</p>

				<input type="submit" value="Accedi" name="bottone" />
				<p><a href="registrazione.jsp">Sei nuovo? Inizia qui!</a></p>
			</div>
		</fieldset>
	</form>
	<%@ include file="diritti.jsp"%>
</body>
</html>