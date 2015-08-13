function initialize() {
	var mapCanvas = document.getElementById('map-canvas');
	var mapOptions = {
		center : new google.maps.LatLng(41.8902102, 12.4922309),
		zoom : 15,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	}
	var map = new google.maps.Map(mapCanvas, mapOptions)
	var iconBase = 'http://maps.google.com/mapfiles/kml/paddle/';

	var place1 = new google.maps.LatLng(41.8902102, 12.4922309);
	var place2 = new google.maps.LatLng(41.8936628, 12.4864929);
	var place3 = new google.maps.LatLng(41.9030632, 12.466276);

	var marker = new google.maps.Marker({
		position : place1,
		map : map,
		icon : iconBase + 'grn-stars.png',
		title : "Colosseo"
	});

	var marker = new google.maps.Marker({
		position : place2,
		map : map,
		icon : iconBase + 'red-stars.png',
		title : "Fori Imperiali"
	});

	var marker = new google.maps.Marker({
		position : place3,
		map : map,
		icon : iconBase + 'blu-stars.png',
		title : "Castel Sant'Angelo"

	});

	var flightPlanCoordinates = [ place1, place2, place3 ];
	var flightPath = new google.maps.Polyline({
		path : flightPlanCoordinates,
		geodesic : true,
		strokeColor : '#FF0000',
		strokeOpacity : 1.0,
		strokeWeight : 2
	});

	flightPath.setMap(map);

}
google.maps.event.addDomListener(window, 'load', initialize);