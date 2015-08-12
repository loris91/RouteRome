function getLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(mia_posizione);
	} else {
		alert("Il browser non supporta la Geolocalizzazione");
	}
}

function mia_posizione(position) {
	var lat = position.coords.latitude;
	var lon = position.coords.longitude;
	alert("Sei stato localizzato correttamente!");
	var elem = document.getElementById("idText");
	elem.value = lat + "," + lon;
}