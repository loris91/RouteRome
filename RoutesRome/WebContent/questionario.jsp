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
			
			<table style="width: 100%">
				<tr>
					<td>Musei</td>
					<td>
						<fieldset>
					        HTML <input type="radio" name="musei" value="1"/>
					        CSS  <input type="radio" name="musei" value="2"/>
					        JavaScript <input type="radio" name="musei" value="3"/>
    					</fieldset>
    				</td>
				</tr>
			</table>
				


		</fieldset>
	</form>
	<%@ include file="diritti.jsp" %>
</body>
</html>