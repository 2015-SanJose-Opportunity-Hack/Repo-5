
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.hackathon.common.form.UserForm"%>
<%@page import="com.hackathon.common.form.BusinessForm"%>
<%
BusinessForm businessForm = (BusinessForm) session.getAttribute("businessForm");
UserForm userForm = (UserForm) session.getAttribute("userForm");
			String lat = "[";
			String lon = "[";
			int j = 0;
				for(String businessName: businessForm.getBusinessName()) {
					lat += "'" + businessForm.getLatitude()[j] + "'" + ",";
					lon += "'" + businessForm.getLongitude()[j] + "'" + ",";
					j++;
				}
				lat = lat.substring(0, lat.length() - 1) + "]";
				lon = lon.substring(0, lon.length() - 1) + "]";
				%>
<script type="text/javascript">
<!--
	var lat = <%=lat%>;
	var lon = <%=lon%>;
//-->
</script>				
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDy9gaCjYpRTOM01f_NCJg_iuDTSsj4GUY&signed_in=true&callback=initMap"
        async defer>
    </script>
	<div id="header">
	  <div class="container_12 nav">	
	    <div id="logo" class="grid_2 alpha">
	      <a href="/web/home"><img src="https://zip.kiva.org/assets/logo-beta-27dc7e5530ca2e00c5e9c43e040bc2b3.jpg" alt="Logo beta" width="100px"></a>	      
	    </div>
	    <div id="logo" class="grid_16 alpha">
	      <a href="/web/home"><img src="https://www.paypalobjects.com/webstatic/i/logo/rebrand/ppcom.svg" alt="PP Logo beta" ></a>
	      <a href="#"><img src="/resources/image/crowd.png" alt="PP Logo beta" ></a>	      
	    </div>
	    		   
	  </div>
	</div>
	<div class="content-menu-bar clean-left">
		<div class="container_12 ">			
			<div id="content_title" class="clearfix">
			  <h1 class="grid_5 heading-text">Choose a Business</h1>
			  <div class="search-box"><img class="search-icon" src="https://groupgifting.paypal-psbc.com/image/icons/Zoom_Icon.png" width="44" height="44" alt="search icon">
			  <form id ="formSearch" action="/web/search" method="post">
			  <input name="key" type="text" placeholder="Business Name" style="color:#4B9123" onblur="pageSubmit('formSearch');">
			  </form>
			  <img class="search-close" src="https://groupgifting.paypal-psbc.com/image/icons/search-close-icon.png" width="35" height="35" alt="search icon"></div>			 
			  </div>
			  <form id ="formCategory" action="/web/searchByCategory" method="post">
			  <div id='cssmenu'>
			  
			  <input type="hidden" id="categoryKey" name="key" value="">
				<ul>
				      
				      
				   <li class='has-sub'><a href='#'><span>Category</span></a>
				      <ul>
				         <li><a href='#' onclick="pageSubmitWithValue('formCategory', 'Food', 'categoryKey','/web/searchByCategory')"><span>Food</span></a></li>
				         <li class='last'><a href='#' onclick="pageSubmitWithValue('formCategory', 'Coffee','categoryKey','/web/searchByCategory')"><span>Coffee</span></a></li>
				      </ul>
				   </li>
				   
				   
				   <li class='has-sub'><a href='#'><span>Filter by</span></a>
				      <ul>
				         <li><a href='#' onclick="pageSubmitWithValue('formCategory', 'Coffee','categoryKey','/web/searchByFilter')"><span>Business Lent</span></a></li>
				         <li class='last' ><a href='#' onclick="pageSubmitWithValue('formCategory', 'Coffee','categoryKey','/web/searchByFilter')"><span>Most popular</span></a></li>
				      </ul>
				   </li>
				   <li class='has-sub'><a href='#'><span>Sort by</span></a>
				      <ul>
				         <li><a href='#' onclick="pageSubmitWithValue('formCategory', 'businessName_ASC','categoryKey','/web/sort')"><span>Business Name [A-Z]</span></a></li>
				         <li class='last'><a href='#' onclick="pageSubmitWithValue('formCategory', 'businessName_DESC','categoryKey','/web/sort')"><span>Business Name [Z-A]</span></a></li>
				         <li class='last'><a href='#' onclick="pageSubmitWithValue('formCategory', 'businessName_ASC','categoryKey','/web/sort')"><span>Near by location</span></a></li>
				         
				      </ul>
				   </li>   
				</ul>
				
			</div>
		  	</form>
			  <!--  -->      
					
		</div>
	</div>
	<div class="content-map-bar clean-left">
	<div id="map" class="container_12" ></div>
	</div>
	<div class="main-content">
		<div class="container_12">
			<div class="content-row clean-left">
			
			<%
			int i = 0;
				for(String businessName: businessForm.getBusinessName()) {
				int column = i%3;
				String name = (businessForm.getOwnerName()[i] + ": " + businessName);
				/* if(name.length() > 26) {
					name = name.substring(0, 26) + "...";
				} */
			%>
				<div id="itemId<%=i%>" class="col-<%=column + 1 %>-business">
					<input type="hidden" id="itemIdKey<%=i%>"  value="<%=businessForm.getId()[i]%>" >
					<img id="tickImage1" src="/resources/image/<%=i+1 %>_0.jpg" class="corner-image">
					<img class="prod-image" src="/resources/image/<%=i+1 %>_1.jpg">		
					<div  class="lightbox clean-left"> 
						<div class="item-title">
							<h2 class="businesNameH2"><%=name %></h2>
							<h3><%=businessForm.getCity()[i] + ", " + businessForm.getState()[i] %></h3>
						</div>
					</div>	
					<div  class="lightbox-adds clean-left">
						<div class="item-adds clean-left">														
							<div id="fb-root"></div>
							<script>(function(d, s, id) {
							  var js, fjs = d.getElementsByTagName(s)[0];
							  if (d.getElementById(id)) return;
							  js = d.createElement(s); js.id = id;
							  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.4";
							  fjs.parentNode.insertBefore(js, fjs);
							}(document, 'script', 'facebook-jssdk'));</script>

							
							<a href="<%=businessForm.getStoreLink()[i] %>" target="_blank" style="margin:0 10px 0 10px;"><img style="" src="/resources/image/shopping_bag_blue.png"></a>
							<%if(StringUtils.isBlank(businessForm.getYelpURL()[i])) {%>							
								<a href="#" target="_blank" onclick="return false;"><img style="background-color: black;" src="/resources/image/yelp.png"></a>
							<%
								} else {
							%>
								<a href="<%=businessForm.getYelpURL()[i] %>" target="_blank" ><img style="background-color: black;" src="/resources/image/yelp.png"></a>
							<%
								} 
							%>							
							<a href="<%=businessForm.getOwnerLink()[i] %>" style="margin:0 10px 0 10px;" target="_blank"><img style="" src="/resources/image/sendemail.png"></a>
							<div class="fb-like" data-href="<%=businessForm.getStoreLink()[i] %>" data-width="50px" data-layout="button_count" data-action="like" data-show-faces="false" data-share="false"></div>
						</div>
					</div>
							
				</div>
				
				<%
				i++;
				}
				%>								
			</div>
		</div>
	</div>
	
	
     

