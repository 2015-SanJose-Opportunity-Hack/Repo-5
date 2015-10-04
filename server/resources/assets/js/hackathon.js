


		function initMap() {
		  var map = new google.maps.Map(document.getElementById('map'), {
		    center: {lat: 37.765521, lng: -122.418460},
		    zoom: 8
		  });
		  var infoWindow = new google.maps.InfoWindow({map: map});
		
		  // Try HTML5 geolocation.
		  if (navigator.geolocation) {
		    navigator.geolocation.getCurrentPosition(function(position) {
		      var pos = {
		        lat: position.coords.latitude,
		        lng: position.coords.longitude
		      };
		
		      infoWindow.setPosition(pos);
		      infoWindow.setContent('PayPal');
		      map.setCenter(pos);
		    }, function() {
		      handleLocationError(true, infoWindow, map.getCenter());
		    });
		    var myLatLng = {lat: 37.765521, lng: -122.418460};
		   addMarker(myLatLng, map);
		  } else {
		    // Browser doesn't support Geolocation
		    handleLocationError(false, infoWindow, map.getCenter());
		  }
		}

		function addMarker( myLatLng,  map) {
			 var marker = new google.maps.Marker({
			        position: myLatLng,
			        map: map,			        
			        animation: google.maps.Animation.DROP,
			        label: 'Chile Lindo Empanadas, San Francisco, CA'
			      });
			 
		}

		
		
		function handleLocationError(browserHasGeolocation, infoWindow, pos) {
		  infoWindow.setPosition(pos);
		  infoWindow.setContent(browserHasGeolocation ?
		                        'Error: The Geolocation service failed.' :
		                        'Error: Your browser doesn\'t support geolocation.');
		}

		
		function openModel(src) {
			$('#myModal').on('show', function () {
		
		        $('iframe').attr("src",src);
		      
			});
		    $('#myModal').modal({show:true})
		}	