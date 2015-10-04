<style type="text/css">
  
  #map { height: 250px; width: 80%; margin: auto;}
</style>

Hello


		<div id="fb-root"></div>
		<script>(function(d, s, id) {
		  var js, fjs = d.getElementsByTagName(s)[0];
		  if (d.getElementById(id)) return;
		  js = d.createElement(s); js.id = id;
		  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.4";
		  fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));</script>

	<div class="fb-like" data-href="http://www.kiva.org/lend/956601" data-width="100px" data-layout="standard" data-action="like" data-show-faces="false" data-share="false"></div>


	<div id="map"></div>
    <script>
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
		    var marker = new google.maps.Marker({
		        position: myLatLng,
		        map: map,
		        label: 'Chile Lindo Empanadas, San Francisco, CA'
		      });
		  } else {
		    // Browser doesn't support Geolocation
		    handleLocationError(false, infoWindow, map.getCenter());
		  }
		}

		function handleLocationError(browserHasGeolocation, infoWindow, pos) {
		  infoWindow.setPosition(pos);
		  infoWindow.setContent(browserHasGeolocation ?
		                        'Error: The Geolocation service failed.' :
		                        'Error: Your browser doesn\'t support geolocation.');
		}

    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDy9gaCjYpRTOM01f_NCJg_iuDTSsj4GUY&signed_in=true&callback=initMap"
        async defer>
    </script>