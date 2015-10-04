


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
		    
		    for(i = 0; i < lat.length; i++) {
		    	var myLatLng = {lat: parseFloat(lat[i]), lng: parseFloat(lon[i])};
		    	addMarker(myLatLng, map);
		    }
		  } else {
		    // Browser doesn't support Geolocation
		    handleLocationError(false, infoWindow, map.getCenter());
		  }
		}

		function addMarker( myLatLng,  map) {
			 var marker = new google.maps.Marker({
			        position: myLatLng,
			        map: map,			        
			        animation: google.maps.Animation.DROP
			        
			      });
			 
		}

		
		
		function handleLocationError(browserHasGeolocation, infoWindow, pos) {
		  infoWindow.setPosition(pos);
		  infoWindow.setContent(browserHasGeolocation ?
		                        'Error: The Geolocation service failed.' :
		                        'Error: Your browser doesn\'t support geolocation.');
		}

		
		
		( function( $ ) {
			$( document ).ready(function() {
			// Cache the elements we'll need
			var menu = $('#cssmenu');
			var menuList = menu.find('ul:first');
			var listItems = menu.find('li').not('#responsive-tab');

			// Create responsive trigger
			menuList.prepend('<li id="responsive-tab"><a href="#">Menu</a></li>');

			// Toggle menu visibility
			menu.on('click', '#responsive-tab', function(){
				listItems.slideToggle('fast');
				listItems.addClass('collapsed');
			});
			});
			} )( jQuery );
		
		function pageSubmit(form) {		    
		    document.getElementById(form).submit();
		    return true;
		}
		function pageSubmitWithValue(form, key, element, action) {
			document.getElementById(element).value=key;
			document.getElementById(form).action = action;
		    document.getElementById(form).submit();
		    return true;
		}
		